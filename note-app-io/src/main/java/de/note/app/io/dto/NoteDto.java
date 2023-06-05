package de.note.app.io.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final @NonNull String title;
    private final @NonNull String body;
}
