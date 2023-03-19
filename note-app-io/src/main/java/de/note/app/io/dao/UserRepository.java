package de.note.app.io.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.note.app.io.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsernameAndPassword(String username, String password);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	Optional<User> findByUsername(String username);
}
