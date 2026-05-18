package xyz.energytrading.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.Map;

/**
 * JSON login filter to accept application/json login requests.
 */
public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * Object-mapper for the JSON-Responses.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Constructor.
     * @param loginUrl URL to listen for login requests
     */
    public JsonUsernamePasswordAuthenticationFilter(String loginUrl) {
        super(request -> "POST".equalsIgnoreCase(request.getMethod()) && loginUrl.equals(request.getServletPath()));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (request.getContentType() != null && request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            Map<String, String> authRequest = objectMapper.readValue(request.getInputStream(), Map.class);
            String username = authRequest.getOrDefault("username", "");
            String password = authRequest.getOrDefault("password", "");

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            token.setDetails(this.authenticationDetailsSource.buildDetails(request));
            return this.getAuthenticationManager().authenticate(token);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws ServletException {
        try {
            this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws ServletException {
        try {
            this.getFailureHandler().onAuthenticationFailure(request, response, failed);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}



