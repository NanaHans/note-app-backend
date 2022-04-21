package de.note.app.io;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.note.app.io.entity.Note;
import de.note.app.io.services.NoteService;

@RestController
@RequestMapping("/api/note/")
public class NoteRestController {

	@Autowired
	NoteService noteService;

	@PostMapping(value = "saveNote")
	Note saveNote(Note note) {
		return this.noteService.saveNote(note);
	}

	@DeleteMapping(value = "deleteNote")
	void deleteNote(long id) {
		this.noteService.deleteNode(id);
	}

	@GetMapping(value = "getNotes")
	List<Note> getNodes() {
		return this.noteService.getAllNotes();
	}

	@PutMapping(value = "updateNote")
	Note updateNote(Note note) {
		return this.noteService.UpDateNote(note);
	}

}
