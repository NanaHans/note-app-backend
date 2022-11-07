/**
 * 
 */
package de.note.app.io.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import de.note.app.io.dao.NoteRepository;
import de.note.app.io.dao.UserRepository;
import de.note.app.io.entity.Note;
import de.note.app.io.entity.User;
import de.note.app.io.services.NoteServiceImpl;

/**
 * @author ${Arsen Nana}
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class NoteServiceImplIntegrationTest {

	@Mock
	private NoteRepository noteRepository;
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private NoteServiceImpl noteServiceImpl;

	@Before
	public void setUp() {

		User user = new User(33L, "teast@gmail.com", "test12", "Max", "Mustermann", "max007");
		Note note1 = new Note(1L, "Test", "SpringBoot Test", user);
		Note note2 = new Note(2L, "Test-1", "SpringBoot Test-1", user);
		List<Note> notes = new ArrayList<>();
		notes.add(note1);
		notes.add(note2);
		user.setNotes(notes);
		Mockito.when(userRepository.findByUsernameAndPassword("max007", "test12")).thenReturn(user);
	}

	@Test
	public void whenValidUsernameAndPassword_thenUserShouldBefound() {
		// given
		String username = "max007";
		String password = "test12";

		// when
		User userFound = userRepository.findByUsernameAndPassword(username, password);

		// then
		assertThat(userFound.getUsername()).isEqualTo(username);

	}

}
