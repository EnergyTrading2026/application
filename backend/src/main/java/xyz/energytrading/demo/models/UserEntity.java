package xyz.energytrading.demo.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity for the Users.
 */
@Entity
@Table(name = "users")
public class UserEntity {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Username.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Password (Hashed and salted)
     */
    @Column(nullable = false)
    private String password;

    /**
     * Set of roles.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    /**
     * Default-Constructor.
     */
    public UserEntity() {}

    /**
     * Constructor with username, password and set of roles.
     * @param username Username
     * @param password Password
     * @param roles Set of Roles
     */
    public UserEntity(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, roles);
    }
}

