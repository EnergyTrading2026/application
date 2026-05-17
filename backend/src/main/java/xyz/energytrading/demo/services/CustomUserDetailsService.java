package xyz.energytrading.demo.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.energytrading.demo.models.UserEntity;
import xyz.energytrading.demo.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom UserDetailsService implementation that loads user details from the database using UserRepository.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * UserRepository to access user data from the database.
     */
    private final UserRepository userRepo;

    /**
     * Constructor.
     * @param userRepo UserRepository
     */
    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user
        UserEntity user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Load authorities
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        // Return UserDetails
        return new User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}

