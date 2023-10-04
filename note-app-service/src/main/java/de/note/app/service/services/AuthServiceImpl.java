package de.note.app.service.services;

import de.note.app.service.dto.LoginDto;
import de.note.app.service.dto.SignedInUserDto;
import de.note.app.service.dto.UserDto;
import de.note.app.service.enums.ResponseMessage;
import de.note.app.service.repository.UserRepository;
import de.note.app.service.security.JwtServiceImpl;
import de.note.app.service.services.common.error.exception.WrongUsernameOrPasswordException;
import de.note.app.service.services.common.message.MessageResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ${Arsen Nana}
 *
 */
@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);


	private final UserRepository userRepos;
	private final JwtServiceImpl jwtServiceImpl;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthServiceImpl(UserRepository userRepos, JwtServiceImpl jwtServiceImpl, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
		this.userRepos = userRepos;
		this.jwtServiceImpl = jwtServiceImpl;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

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
		de.note.app.service.entities.User user = this.modelMapper.map(userDto, de.note.app.service.entities.User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepos.save(user);
		return ResponseEntity.ok(new MessageResponse(ResponseMessage.REGISTER_SUCCESS.getId(),
				ResponseMessage.REGISTER_SUCCESS.getMessage()));

	}

	@Override
	public ResponseEntity<SignedInUserDto> login(LoginDto loginDto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		User authUser = (User) authentication.getPrincipal();

	de.note.app.service.entities.User userEntity = this.userRepos.findByUsername(loginDto.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));
		LOGGER.info("### password encoded {}",
				passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword()));

		SignedInUserDto signedInUserDto = modelMapper.map(userEntity, SignedInUserDto.class);
		if (signedInUserDto != null && signedInUserDto.getId() != null) {
			String jwtToken = this.jwtServiceImpl.generateJwt(signedInUserDto.getId(), signedInUserDto.getUsername());
			signedInUserDto.setJwtToken(jwtToken);
			return ResponseEntity.ok(signedInUserDto);
		}

		throw new WrongUsernameOrPasswordException();
	}

}
