package de.note.app.io.services;

import java.nio.charset.StandardCharsets;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.hash.Hashing;

import de.note.app.io.dao.UserRepository;
import de.note.app.io.dto.LoginDto;
import de.note.app.io.dto.SignedInUserDto;
import de.note.app.io.dto.UserDto;
import de.note.app.io.entity.User;
import de.note.app.io.enums.ResponseMessage;
import de.note.app.io.services.common.error.exception.WrongUsernameOrPasswordException;
import de.note.app.io.services.common.message.MessageResponse;

/**
 * 
 * @author ${Arsen Nana}
 *
 */
@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepos;
	@Autowired
	private JwtServiceImpl jwtServiceImpl;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public ResponseEntity<MessageResponse> registerUser(UserDto userDto) {
		if (Boolean.TRUE.equals(this.userRepos.existsByEmail(userDto.getEmail()))) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(
					ResponseMessage.REGISTER_EMAIL.getId(), ResponseMessage.REGISTER_EMAIL.getMessage()));
		}
		if (Boolean.TRUE.equals(this.userRepos.existsByUsername(userDto.getUsername()))) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(
					ResponseMessage.REGISTER_USERNAME.getId(), ResponseMessage.REGISTER_USERNAME.getMessage()));
		}
		User user = this.modelMapper.map(userDto, User.class);
		String passwordHashed = hashPassword(userDto.getPassword());
		user.setPassword(passwordHashed);
		userRepos.save(user);
		return ResponseEntity.ok(new MessageResponse(ResponseMessage.REGISTER_SUCCESS.getId(),
				ResponseMessage.REGISTER_SUCCESS.getMessage()));

	}

	@Override
	public SignedInUserDto login(LoginDto loginDto) {
		User user = this.userRepos.findByUsernameAndPassword(loginDto.getUsername(),
				hashPassword(loginDto.getPassword()));
		if (user != null && user.getId() != null) {
			SignedInUserDto signedInUserDto = this.modelMapper.map(user, SignedInUserDto.class);
			signedInUserDto.setId(user.getId());
			String jwtToken = this.jwtServiceImpl.generateJwt(user.getId());

			signedInUserDto.setJwtToken(jwtToken);
			return signedInUserDto;
		}
		throw new WrongUsernameOrPasswordException();
	}

	private String hashPassword(String password) {
		return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
	}

}
