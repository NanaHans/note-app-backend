package de.note.app.io.services;

import java.nio.charset.StandardCharsets;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

import de.note.app.io.dao.UserRepository;
import de.note.app.io.dto.LoginDto;
import de.note.app.io.dto.SignedInUserDto;
import de.note.app.io.dto.UserDto;
import de.note.app.io.entity.User;
import de.note.app.io.services.common.error.exception.WrongUsernameOrPasswordException;
import de.note.app.io.services.common.message.MessageResponse;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepos;
	@Autowired
	private JwtServiceImpl jwtServiceImpl;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public ResponseEntity<?> registerUser(UserDto userDto) {
		if (Boolean.TRUE.equals(this.userRepos.existsByUsername(userDto.getUsername()))) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if (Boolean.TRUE.equals(this.userRepos.existsByEmail(userDto.getEmail()))) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		User user = this.modelMapper.map(userDto, User.class);
		String passwordHashed = Hashing.sha256().hashString(userDto.getPassword(), StandardCharsets.UTF_8).toString();
		user.setPassword(passwordHashed);
		this.userRepos.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}

	@Override
	public SignedInUserDto login(LoginDto loginDto) {
		User user = this.userRepos.findByUsernameAndPassword(loginDto.getUsername(),
				Hashing.sha256().hashString(loginDto.getPassword(), StandardCharsets.UTF_8).toString());
		if (user != null && user.getId() != null) {
			SignedInUserDto signedInUserDto = this.modelMapper.map(user, SignedInUserDto.class);
			signedInUserDto.setId(user.getId());
			String jwtToken = this.jwtServiceImpl.generateJwt(user.getId());

			signedInUserDto.setJwtToken(jwtToken);
			return signedInUserDto;
		}
		throw new WrongUsernameOrPasswordException();
	}

}
