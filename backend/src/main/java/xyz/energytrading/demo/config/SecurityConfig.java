package xyz.energytrading.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import xyz.energytrading.demo.security.JsonUsernamePasswordAuthenticationFilter;
import xyz.energytrading.demo.services.CustomUserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Security-Configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * User details service to load user information from the database for authentication and authorization.
     */
    private final CustomUserDetailsService userDetailsService;

    /**
     * Constructor.
     */
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /*
     * Configure the security filter chain, including CSRF, CORS, authentication, and authorization rules.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
                // Configure CSRF to ignore the login endpoint and use cookies to store the CSRF token
                .csrf(csrf -> csrf
                        // Allow login
                        .ignoringRequestMatchers(request ->
                                "POST".equalsIgnoreCase(request.getMethod())
                                        && "/api/auth/login".equals(request.getServletPath()))
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                // Configure CORS to allow requests from the frontend application, with credentials support
                .cors(Customizer.withDefaults())
                // For REST API calls we want 401 instead of redirects to a login page (302)
                .exceptionHandling(ex -> ex.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                // Configure Authorization
                .authorizeHttpRequests(auth -> auth
                        // Permit public API calls
                        .requestMatchers("/api/auth/login", "/api/public/**").permitAll()
                        // Only allow admin API calls to users with ADMIN role
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        // All other API calls require authentication
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().authenticated()
                )
                // Configure login form
                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .successHandler(jsonAuthSuccessHandler())
                        .failureHandler(jsonAuthFailureHandler())
                        .permitAll()
                )
                // Configure logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                        .deleteCookies("JSESSIONID")
                )
                .userDetailsService(userDetailsService);

        // Register JSON login filter to accept application/json login requests at /api/auth/login
        JsonUsernamePasswordAuthenticationFilter jsonFilter = new JsonUsernamePasswordAuthenticationFilter("/api/auth/login");
        jsonFilter.setAuthenticationManager(authenticationManager);
        jsonFilter.setAuthenticationSuccessHandler(jsonAuthSuccessHandler());
        jsonFilter.setAuthenticationFailureHandler(jsonAuthFailureHandler());
        http.addFilterBefore(jsonFilter, UsernamePasswordAuthenticationFilter.class);

        // Register DaoAuthenticationProvider with BCrypt to ensure password hashing is checked correctly
        http.authenticationProvider(daoAuthenticationProvider());

        return http.build();
    }

    /**
     * Registers the CORS filter with the highest precedence to ensure it runs before Spring Security filters, allowing CORS preflight requests to be handled properly.
     * @param corsConfigurationSource CorsConfigurationSource
     * @return A FilterRegistrationBean that registers the CorsFilter with the specified CORS configuration and order.
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean(CorsConfigurationSource corsConfigurationSource) {
        CorsFilter filter = new CorsFilter(corsConfigurationSource);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    /**
     * Exposes the AuthenticationManager bean, which is required for the custom JSON authentication filter to perform authentication.
     * It retrieves the AuthenticationManager from the AuthenticationConfiguration provided by Spring Security.
     * @param authenticationConfiguration AuthenticationConfiguration
     * @return The AuthenticationManager bean that can be used for authentication in custom filters or other components.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configures a DaoAuthenticationProvider that uses the custom UserDetailsService and BCryptPasswordEncoder for authentication.
     * @return DaoAuthenticationProvider that uses the custom UserDetailsService and BCryptPasswordEncoder for authentication.
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(this.userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Defines a custom AuthenticationSuccessHandler that returns a JSON response containing the authenticated user's username and roles upon successful authentication.
     * @return AuthenticationSuccessHandler that returns a JSON response containing the authenticated user's username and roles upon successful authentication.
     */
    @Bean
    public AuthenticationSuccessHandler jsonAuthSuccessHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json");
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            List<String> roles = principal.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            Map<String, Object> payload = Map.of("username", principal.getUsername(), "roles", roles);
            new ObjectMapper().writeValue(response.getOutputStream(), payload);
        };
    }

    /**
     * Defines a custom AuthenticationFailureHandler that returns a JSON response with an error message when authentication fails, along with a 401 Unauthorized status code.
     * @return Custom AuthenticationFailureHandler that returns a JSON response with an error message when authentication fails
     */
    @Bean
    public AuthenticationFailureHandler jsonAuthFailureHandler() {
        return (request, response, exception) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(), Map.of("error", "Authentication failed", "message", exception.getMessage()));
        };
    }

    /**
     * Defines a PasswordEncoder bean that uses BCrypt hashing algorithm to securely hash passwords.
     * @return PasswordEncoder bean that uses BCrypt hashing algorithm to securely hash passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines a CorsConfigurationSource bean that configures CORS settings for the frontend.
     * @param frontendOrigin Frontend Origin.
     * @return CorsConfigurationSource bean that configures CORS settings for the frontend.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource(@Value("${app.frontend.origin}") String frontendOrigin) {
        CorsConfiguration cfg = new CorsConfiguration();
        List<String> origins = List.of(frontendOrigin.split(","));
        List<String> trimmed = origins.stream().map(String::trim).filter(s -> !s.isEmpty()).toList();
        List<String> patterns = new java.util.ArrayList<>(trimmed);
        cfg.setAllowedOriginPatterns(patterns);
        cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        cfg.setAllowedHeaders(List.of("*"));
        cfg.setAllowCredentials(true);
        cfg.setExposedHeaders(List.of("XSRF-TOKEN"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }
}



