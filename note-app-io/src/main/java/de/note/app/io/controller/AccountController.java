package de.note.app.io.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.note.app.io.dto.LoginDto;
import de.note.app.io.dto.UserDto;
import de.note.app.io.entity.User;
import de.note.app.io.services.AccountService;

@RestController
@RequestMapping("/api/account/")
public class AccountController {

	@Autowired
	private AccountService accountService;

	public AccountController() {

	}

	@PostMapping(value = "saveUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public User saveUser(@RequestBody UserDto userDto) {
		return this.accountService.registerUser(userDto);
	}

	@PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDto login(@RequestBody LoginDto loginDto) {

		return new UserDto();
	}
}
