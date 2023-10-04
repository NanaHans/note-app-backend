package de.note.app.service.repository;

import de.note.app.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsernameAndPassword(String username, String password);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Optional<User> findByUsername(String username);
}
