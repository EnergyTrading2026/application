package xyz.energytrading.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller for authentication.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /**
     * AuthenticationManager.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Constructor.
     * @param authenticationManager AuthenticationManager
     */
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Process Login Request.
     * @param body Request-Body
     * @param request HttpServletRequest
     * @return Result of the authentication.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String username = body.getOrDefault("username", "");
        String password = body.getOrDefault("password", "");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            // ensure session is created so JSESSIONID is returned
            request.getSession(true);
            List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            return ResponseEntity.ok(Map.of("username", auth.getName(), "roles", roles));
        } catch (Exception ex) {
            return ResponseEntity.status(401).body(Map.of("error", "Authentication failed", "message", ex.getMessage()));
        }
    }

    /**
     * Process Logout Request.
     * @param request HttpServletRequest
     * @return Result of the logout
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        try {
            // Clear Spring Security context
            SecurityContextHolder.clearContext();
            // Invalidate session if present
            var session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            return ResponseEntity.ok(Map.of("ok", true));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Logout failed", "message", ex.getMessage()));
        }
    }

    /**
     * Get current user info.
     * @param authentication Authentication
     * @return Current user info or 401 if not authenticated.
     */
    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        List<String> roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("username", principal.getUsername(), "roles", roles));
    }
}
