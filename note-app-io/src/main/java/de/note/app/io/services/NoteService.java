package de.note.app.io.services;

import java.util.List;

import de.note.app.io.dto.NoteDto;
import de.note.app.io.entity.Note;

public interface NoteService {

	Note saveNote(long userId, NoteDto note);

	Note findNoteByTitle(String title);

	Note upDateNote(long userId, NoteDto note);

	List<Note> getAllNotes();

	void deleteNode(long userId, long nodeId);
}
