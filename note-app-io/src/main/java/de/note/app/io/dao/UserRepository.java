package de.note.app.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.note.app.io.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsernameAndPassword(String username, String password);
}
