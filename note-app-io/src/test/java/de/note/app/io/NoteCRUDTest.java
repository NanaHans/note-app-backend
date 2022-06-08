package de.note.app.io;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.note.app.io.dao.NoteRepository;
import de.note.app.io.dao.UserRepository;
import de.note.app.io.entity.Note;
import de.note.app.io.entity.User;
import de.note.app.io.services.NoteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteCRUDTest {

	@Autowired
	NoteRepository noteRepository;
	@Autowired
	NoteService noteService;
	@Autowired
	UserRepository userRepository;

	// @Test
	public void saveNote() {
		this.noteRepository.save(new Note("Spring Boot", "Einführung in Spring Boot"));
		Note noteFound = this.noteService.findNoteByTitle("Spring Boot");
		assertThat(noteFound.getTitle()).isEqualTo("Spring Boot");
	}

	// @Test
	public void saveUser() {
		User user = this.userRepository.save(generatedUser());
		assertThat(user.getFirstname()).isEqualTo("Frida");
	}

	// @Test
	public void saveNoteAndUser() {
		User user = this.userRepository.findById(2L).get();
		Note note = generateNote();
		note.setUser(user);
		this.noteRepository.save(note);
	}

	private static User generatedUser() {
		User user = new User();
		user.setFirstname("Frida");
		user.setLastname("Nana");
		user.setUsername("alaine");
		user.setEmail("fridanana@gmail.com");
		user.setPassword("frida@007");

		return user;
	}

	private static Note generateNote() {
		Note note = new Note();
		note.setTitle("Spring Boot");
		note.setBody("Spring Security");
		return note;
	}

}
