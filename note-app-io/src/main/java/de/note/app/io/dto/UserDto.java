package de.note.app.io.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Long id;
	private final @NonNull String email;
	private final @NonNull String password;
	private final @NonNull String firstname;
	private final @NonNull String lastname;
	private final @NonNull String username;

}
