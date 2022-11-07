/**
 * 
 */
package de.note.app.io.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import de.note.app.io.dao.NoteRepository;
import de.note.app.io.entity.Note;
import de.note.app.io.services.NoteService;
import de.note.app.io.services.NoteServiceImpl;

/**
 * @author ${Arsen Nana}
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteServiceImplIntegrationTest {

	@TestConfiguration
	static class NoteServiceImplContextConfiguration {
		@Bean
		public NoteService noteService() {
			return new NoteServiceImpl();
		}
	}

	@Autowired
	private NoteService noteService;
	@MockBean
	private NoteRepository noteRepository;

	@Before
	public void setUp() {
		Note note = new Note("Unit", "Test");
		Mockito.when(noteRepository.findNoteByTitle(note.getTitle())).thenReturn(note);
	}

	@Test
	public void whenValidTitle_thenNoteshouldBefound() {
		// given
		String title = "Unit";

		// when
		Note noteFound = noteService.findNoteByTitle(title);

		// then
		assertThat(noteFound.getTitle()).isEqualTo(title);

	}

}
