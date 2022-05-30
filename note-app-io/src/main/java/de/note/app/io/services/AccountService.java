package de.note.app.io.services;

import de.note.app.io.dto.LoginDto;
import de.note.app.io.dto.UserDto;
import de.note.app.io.entity.User;

public interface AccountService {

	public User registerUser(UserDto userDto);

	public User login(LoginDto loginDto);
}
