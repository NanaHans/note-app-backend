/**
 * 
 */
package de.note.app.io.service.integration;

import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author ${Arsen Nana}
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { IntegrationTestConfig.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class })
public abstract class BasicIntagrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	@Value("${note.app.user.name}")
	private String INTEGRATION_TEST_USER;
	@Value("${note.app.user.password.decoded}")
	private String INTEGRATION_TEST_PASSWORD;

	@BeforeClass
	@FlywayTest
	public static void beforeclass() {

	}
}
