package de.note.app.io.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SignedInUserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private List<NoteDto> notes = new ArrayList<>();
	private String jwtToken;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public List<NoteDto> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteDto> notes) {
		this.notes = notes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
