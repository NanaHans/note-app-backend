package de.note.app.io.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.note.app.io.dto.NoteDto;
import de.note.app.io.entity.Note;
import de.note.app.io.services.NoteService;

@RestController
@RequestMapping("/api/note/")
public class NoteRestController {

	@Autowired
	private NoteService noteService;

	@PostMapping(value = "saveNote", consumes = MediaType.APPLICATION_JSON_VALUE)
	Note saveNote(@RequestBody NoteDto note) {
		return this.noteService.saveNote(note);
	}

	@DeleteMapping(value = "deleteNote/id/{id}")
	void deleteNote(@PathVariable("id") long id) {
		this.noteService.deleteNode(id);
	}

	@GetMapping(value = "getNotes")
	List<Note> getNodes() {
		return this.noteService.getAllNotes();
	}

	@PutMapping(value = "updateNote")
	Note updateNote(@RequestBody NoteDto note) {
		return this.noteService.UpDateNote(note);
	}

}
