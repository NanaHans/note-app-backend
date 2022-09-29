package de.note.app.io.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.note.app.io.dao.NoteRepository;
import de.note.app.io.dto.NoteDto;
import de.note.app.io.entity.Note;

/**
 * 
 * @author ${Arsen Nana}
 *
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private ModelMapper modelMapper;
	private Note noteEntity;

	@Override
	public Note findNoteByTitle(String title) {
		return this.noteRepository.findNoteByTitle(title);
	}

	@Override
	public Note UpDateNote(NoteDto note) {
		noteEntity = noteRepository.getById(note.getId());
		noteEntity.setTitle(note.getTitle());
		noteEntity.setBody(note.getBody());
		return noteRepository.save(noteEntity);
	}

	@Override
	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}

	@Override
	public void deleteNode(long id) {
		noteRepository.deleteById(id);
	}

	@Override
	public Note saveNote(NoteDto note) {
		noteEntity = modelMapper.map(note, Note.class);
		return noteRepository.save(noteEntity);
	}

}
