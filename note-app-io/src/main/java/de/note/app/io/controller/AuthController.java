package de.note.app.io.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.note.app.io.dto.LoginDto;
import de.note.app.io.dto.SignedInUserDto;
import de.note.app.io.dto.UserDto;
import de.note.app.io.entity.User;
import de.note.app.io.services.AuthService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

	@Autowired
	private AuthService authService;

	public AuthController() {

	}

	@PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
	public User saveUser(@RequestBody UserDto userDto) {
		return this.authService.registerUser(userDto);
	}

	@PostMapping(value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
	public SignedInUserDto login(@RequestBody LoginDto loginDto) {
		SignedInUserDto signedInUserDto = this.authService.login(loginDto);
		return signedInUserDto;
	}
}
