package de.note.app.io.services;

import java.util.List;

import de.note.app.io.entity.Note;

public interface NoteService {

	Note saveNote(Note note);

	Note findNoteByTitle(String title);

	Note UpDateNote(Note note);

	List<Note> getAllNotes();

	void deleteNode(long id);
}
