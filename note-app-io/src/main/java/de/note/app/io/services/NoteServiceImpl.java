package de.note.app.io.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.note.app.io.dao.NoteRepository;
import de.note.app.io.dao.UserRepository;
import de.note.app.io.dto.NoteDto;
import de.note.app.io.entity.Note;
import de.note.app.io.entity.User;

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
	@Autowired
	private UserRepository userRepository;
	private Note noteEntity;
	private User user;

	@Override
	public Note findNoteByTitle(String title) {
		return this.noteRepository.findNoteByTitle(title);
	}

	@Override
	public Note upDateNote(long userId, NoteDto noteDto) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			user = userOptional.get();
			Optional<Note> noteOptional = user.getNotes().stream().filter(n -> n.getId().equals(noteDto.getId()))
					.findFirst();
			if (noteOptional.isPresent()) {
				noteEntity = noteOptional.get();
				noteEntity.setTitle(noteDto.getTitle());
				noteEntity.setBody(noteDto.getBody());
				noteRepository.save(noteEntity);

			}
		}
		return noteEntity;
	}

	@Override
	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}

	@Override
	public void deleteNode(long userId, long noteId) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			user = userOptional.get();
			Optional<Note> noteOptional = user.getNotes().stream().filter(n -> n.getId() == noteId).findFirst();
			if (noteOptional.isPresent()) {
				noteEntity = noteOptional.get();
				user.getNotes().remove(noteEntity);
				userRepository.save(user);
				noteRepository.delete(noteEntity);
			}

		}

	}

	@Override
	public Note saveNote(long userId, NoteDto noteDto) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			noteEntity = modelMapper.map(noteDto, Note.class);
			noteEntity.setUser(userOptional.get());
			noteEntity = noteRepository.save(noteEntity);
		}
		return noteEntity;
	}

}
