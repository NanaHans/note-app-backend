package de.note.app.io.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.note.app.io.dto.NoteDto;
import de.note.app.io.entity.Note;
import de.note.app.io.security.impl.JwtServiceImpl;
import de.note.app.io.services.NoteService;
import de.note.app.io.services.common.error.exception.UnauthorizedException;

@RestController
@RequestMapping("/api/note/")
@RequiredArgsConstructor
public class NoteRestController {
    private static final String INVALID_JWT_TOKEN = "Invalid JWT-token: ";

    private final NoteService noteService;
    private final JwtServiceImpl jwtService;
    private final ModelMapper modelMapper;


    /**
     * saves a new note {@link Note}
     *
     * @param request contains header-informations
     * @param noteDto new note to be saved
     * @return the saved note
     */
    @PostMapping(value = "saveNote", consumes = MediaType.APPLICATION_JSON_VALUE)
    public NoteDto saveNote(HttpServletRequest request, @RequestBody NoteDto noteDto) {
        String token = jwtService.extractTokenFromRequest(request);
        Long userId = jwtService.getUserId(token);
        if (token.isBlank()) {
            throw new UnauthorizedException(new Throwable(INVALID_JWT_TOKEN + token));
        } else {
            return modelMapper.map(noteService.saveNote(userId, noteDto), noteDto.getClass());
        }
    }

    /**
     * deletes a {@link Note} by a given ID
     *
     * @param request provides header-informations
     * @param id      id of the note to be deleted
     */
    @DeleteMapping(value = "deleteNote/id/{id}")
    void deleteNote(HttpServletRequest request, @PathVariable("id") long id) {
        String token = jwtService.extractTokenFromRequest(request);
        long userId = jwtService.getUserId(token);
        if (token.isBlank()) {
            throw new UnauthorizedException(new Throwable(INVALID_JWT_TOKEN + token));
        } else {
            this.noteService.deleteNode(userId, id);
        }
    }

    /**
     * Updates a given note
     *
     * @param request provides request information
     * @param noteDto contains new data
     * @return return the data saved as {@link NoteDto}
     */
    @PutMapping(value = "updateNote")
    public NoteDto updateNote(HttpServletRequest request, @RequestBody NoteDto noteDto) {
        String token = jwtService.extractTokenFromRequest(request);
        long userId = jwtService.getUserId(token);
        if (token.isBlank()) {
            throw new UnauthorizedException(new Throwable(INVALID_JWT_TOKEN + token));
        } else {
            return modelMapper.map(noteService.upDateNote(userId, noteDto), noteDto.getClass());
        }
    }

}
