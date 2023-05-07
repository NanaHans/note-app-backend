/**
 * 
 */
package de.note.app.io.service.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import de.note.app.io.dao.UserRepository;
import de.note.app.io.dto.UserDto;

/**
 * @author ${Arsen Nana}
 *
 */
class AuthControllerTest extends BasicIntegrationTest {

	@Autowired
	private UserRepository userRepository;
	private HttpEntity<?> httpEntity;

	@Test
	@FlywayTest(locationsForMigrate = { "classpath:integration/schema" })
	void when_emailNotExist_then_createUserAndReturnHttpStatusOk() {
		// given
		int EXPECTED_AMOUNT_BEFORE_POST = 1;
		givenNewUser();

		// when
		assertThat(EXPECTED_AMOUNT_BEFORE_POST).isEqualTo(userRepository.count());
		ResponseEntity<?> response = testRestTemplate.exchange("/api/auth/signup", HttpMethod.POST, httpEntity,
				HttpEntity.class);

		// then
		assertThat(EXPECTED_AMOUNT_BEFORE_POST + 1).isEqualTo(userRepository.count());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "classpath:integration/schema" })
	void when_emailExist_then_throwExceptionAndReturnHttpStatusConflict() {
		// given
		int EXPECTED_AMOUNT_BEFORE_POST = 1;
		givenNewUserWithExistingEmail();

		// when
		assertThat(EXPECTED_AMOUNT_BEFORE_POST).isEqualTo(userRepository.count());
		ResponseEntity<?> response = testRestTemplate.exchange("/api/auth/signup", HttpMethod.POST, httpEntity,
				HttpEntity.class);

		// then
		assertThat(EXPECTED_AMOUNT_BEFORE_POST).isEqualTo(userRepository.count());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
	}

	@Test
	@FlywayTest(locationsForMigrate = { "classpath:integration/schema" })
	void when_usernameExist_then_throwExceptionAndReturnHttpStatusConflict() {
		// given
		int EXPECTED_AMOUNT_BEFORE_POST = 1;
		givenNewUserWithExistingUserName();

		// when
		assertThat(EXPECTED_AMOUNT_BEFORE_POST).isEqualTo(userRepository.count());
		ResponseEntity<?> response = testRestTemplate.exchange("/api/auth/signup", HttpMethod.POST, httpEntity,
				HttpEntity.class);

		// then
		assertThat(EXPECTED_AMOUNT_BEFORE_POST).isEqualTo(userRepository.count());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
	}

	private void givenNewUser() {
		httpEntity = new HttpEntity<>(new UserDto("test@gmail.com", "test123", "Testermann", "TestMax", "test123"));
	}

	private void givenNewUserWithExistingEmail() {
		httpEntity = new HttpEntity<>(
				new UserDto("maxmustermann@gmail.com", "test123", "Testermann", "TestMax", "test123"));
	}

	private void givenNewUserWithExistingUserName() {
		httpEntity = new HttpEntity<>(new UserDto("test@gmail.com", "test123", "Testermann", "TestMax", "tirone007"));
	}

}
