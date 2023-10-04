package de.note.app.service.services;

import de.note.app.service.dto.NoteDto;
import de.note.app.service.entities.Note;

import java.util.List;

public interface NoteService {

	Note saveNote(long userId, NoteDto note);

	Note findNoteByTitle(String title);

	Note upDateNote(long userId, NoteDto note);

	List<Note> getAllNotes();

	void deleteNode(long userId, long nodeId);
}
