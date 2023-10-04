package de.note.app.service.controller;

import de.note.app.service.dto.NoteDto;
import de.note.app.service.security.JwtServiceImpl;
import de.note.app.service.services.NoteService;
import de.note.app.service.services.common.error.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/note/")
public class NoteRestController {
    private static final String INVALID_JWT_TOKEN = "Invalid JWT-token: ";

    private final NoteService noteService;
    private final JwtServiceImpl jwtService;
    private final ModelMapper modelMapper;

    @Autowired
    public NoteRestController(NoteService noteService, JwtServiceImpl jwtService, ModelMapper modelMapper) {
        this.noteService = noteService;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }

    /**
     * saves a new note {@link de.note.app.service.entities.Note}
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
