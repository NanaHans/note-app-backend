/**
 * 
 */
package de.note.app.io.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import de.note.app.io.dao.NoteRepository;
import de.note.app.io.entity.Note;
import de.note.app.io.services.NoteService;

/**
 * @author ${Arsen Nana}
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class NoteServiceImplIntegrationtest2 {

	@InjectMocks
	private NoteService noteService;
	@Mock
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
