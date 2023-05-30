/**
 * 
 */
package de.note.app.io.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import de.note.app.io.dao.UserRepository;
import de.note.app.io.entity.Note;
import de.note.app.io.entity.User;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author ${Arsen Nana}
 *
 */
@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {
	@Mock
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {

		User user = new User("teast@gmail.com", "test12", "Max", "Mustermann", "max007");
		user.setId(33L);
		Note note1 = new Note("Test", "SpringBoot Test", user);
		note1.setId(1L);
		Note note2 = new Note("Test-1", "SpringBoot Test-1", user);
		note2.setId(2L);
		List<Note> notes = new ArrayList<>();
		notes.add(note1);
		notes.add(note2);
		user.setNotes(notes);
		Mockito.when(userRepository.findByUsernameAndPassword("max007", "test12")).thenReturn(user);
	}

	@Test
	void whenValidUsernameAndPassword_thenUserShouldBefound() {
		// given
		String username = "max007";
		String password = "test12";

		// when
		User userFound = userRepository.findByUsernameAndPassword(username, password);

		// then
		assertThat(userFound.getUsername()).isEqualTo(username);

	}

}
