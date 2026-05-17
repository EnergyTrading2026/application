package xyz.energytrading.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.energytrading.demo.models.UserEntity;

import java.util.Optional;

/**
 * Repository for managing the users.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}

