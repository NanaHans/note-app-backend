package de.note.app.io.services;

import org.springframework.http.ResponseEntity;

import de.note.app.io.dto.LoginDto;
import de.note.app.io.dto.SignedInUserDto;
import de.note.app.io.dto.UserDto;
import de.note.app.io.services.common.message.MessageResponse;

public interface AuthService {

	public ResponseEntity<MessageResponse> registerUser(UserDto userDto);

	public ResponseEntity<SignedInUserDto> login(LoginDto loginDto);
}
