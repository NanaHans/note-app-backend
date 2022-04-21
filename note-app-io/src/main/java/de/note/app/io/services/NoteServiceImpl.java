package de.note.app.io.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.note.app.io.dao.NoteRepository;
import de.note.app.io.entity.Note;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	NoteRepository noteRepository;

	@Override
	public Note findNoteByTitle(String title) {
		return this.noteRepository.findNoteByTitle(title);
	}

	@Override
	public Note UpDateNote(Note note) {
		return this.noteRepository.save(note);
	}

	@Override
	public List<Note> getAllNotes() {
		return this.noteRepository.findAll();
	}

	@Override
	public void deleteNode(long id) {
		this.noteRepository.deleteById(id);
	}

	@Override
	public Note saveNote(Note note) {
		return this.noteRepository.save(note);
	}

}
