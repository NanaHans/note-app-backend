package de.note.app.io;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.note.app.io.dao.NoteRepository;
import de.note.app.io.entity.Note;
import de.note.app.io.services.NoteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteCRUDTest {

	@Autowired
	NoteRepository noteRepository;
	@Autowired
	NoteService noteService;

	@SuppressWarnings({ "deprecation", "unlikely-arg-type" })
	@Test
	public void saveNote() {
		// this.noteRepository.save(new Note("Spring Boot", "Einführung in Spring
		// Boot"));
		Note noteFound = this.noteService.findNoteByTitle("Spring Boot");
		assertThat(noteFound.getTitle()).isEqualTo("Spring Boot");
	}
}
