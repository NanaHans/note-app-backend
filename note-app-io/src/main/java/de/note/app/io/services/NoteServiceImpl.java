package de.note.app.io.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.note.app.io.dao.NoteRepository;
import de.note.app.io.dto.NoteDto;
import de.note.app.io.entity.Note;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepository;
	private Note noteEntity;

	private ModelMapper modelMapper = new ModelMapper();;

	@Override
	public Note findNoteByTitle(String title) {
		return this.noteRepository.findNoteByTitle(title);
	}

	@Override
	public Note UpDateNote(NoteDto note) {
		Note noteEntity = this.noteRepository.getById(note.getId());
		noteEntity.setTitle(note.getTitle());
		noteEntity.setBody(note.getBody());
		return this.noteRepository.save(noteEntity);
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
	public Note saveNote(NoteDto note) {
		this.noteEntity = modelMapper.map(note, Note.class);
		return this.noteRepository.save(noteEntity);
	}

}
