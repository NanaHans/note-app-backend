package de.note.app.service.services;

import de.note.app.service.dto.LoginDto;
import de.note.app.service.dto.SignedInUserDto;
import de.note.app.service.dto.UserDto;
import de.note.app.service.services.common.message.MessageResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

	public ResponseEntity<MessageResponse> registerUser(UserDto userDto);

	public ResponseEntity<SignedInUserDto> login(LoginDto loginDto);
}
