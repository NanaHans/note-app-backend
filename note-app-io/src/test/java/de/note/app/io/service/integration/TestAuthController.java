/**
 * 
 */
package de.note.app.io.service.integration;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Assert;
import org.junit.Test;
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
public class TestAuthController extends BasicIntagrationTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@FlywayTest(locationsForMigrate = { "classpath:integration/schema" })
	public void whenPostNewUserThenCreateInDB() {
		// given
		int EXPECTED_AMOUNT_BEFORE_POST = 2;

		// when
		Assert.assertEquals(EXPECTED_AMOUNT_BEFORE_POST, userRepository.count());
		ResponseEntity<?> response = testRestTemplate.exchange("/api/auth/signup", HttpMethod.POST,
				prepareRequestEntityFornewUserData(), HttpEntity.class);

		// then
		Assert.assertEquals(EXPECTED_AMOUNT_BEFORE_POST + 1, userRepository.count());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	private HttpEntity<?> prepareRequestEntityFornewUserData() {
		return new HttpEntity<>(new UserDto("test@gmail.com", "test123", "Mutermann", "Max", "maxmusterMann"));
	}

}
