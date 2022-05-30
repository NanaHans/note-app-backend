package de.note.app.io.services;

import java.util.List;

import de.note.app.io.dto.NoteDto;
import de.note.app.io.entity.Note;

public interface NoteService {

	Note saveNote(NoteDto note);

	Note findNoteByTitle(String title);

	Note UpDateNote(NoteDto note);

	List<Note> getAllNotes();

	void deleteNode(long id);
}
