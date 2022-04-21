package de.note.app.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.note.app.io.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	Note findNoteByTitle(String title);
}
