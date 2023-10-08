package de.note.app.io.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@Setter
public class LoginDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	private final @NonNull String username;
	private final @NonNull String password;


}
