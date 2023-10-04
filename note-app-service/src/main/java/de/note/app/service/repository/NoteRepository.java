package de.note.app.service.repository;


import de.note.app.service.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	Note findNoteByTitle(String title);
}
