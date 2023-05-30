package de.note.app.io.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Long id;
	private final String title;
	private final String body;



}
