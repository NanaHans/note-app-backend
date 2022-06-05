package de.note.app.io.services;

import de.note.app.io.dto.LoginDto;
import de.note.app.io.dto.SignedInUserDto;
import de.note.app.io.dto.UserDto;
import de.note.app.io.entity.User;

public interface AuthService {

	public User registerUser(UserDto userDto);

	public SignedInUserDto login(LoginDto loginDto);
}
