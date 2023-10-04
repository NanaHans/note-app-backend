package de.note.app.service.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@Setter
public class LoginDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private final @NonNull String username;
	private final @NonNull String password;


}
