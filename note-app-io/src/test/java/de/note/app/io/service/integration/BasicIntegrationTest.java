/**
 * 
 */
package de.note.app.io.service.integration;

import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author ${Arsen Nana}
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { IntegrationTestConfig.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class })
public abstract class BasicIntegrationTest {

	@Autowired
	protected TestRestTemplate testRestTemplate;
	@Value("${note.app.user.name}")
	protected String INTEGRATION_TEST_USER;
	@Value("${note.app.user.password.decoded}")
	protected String INTEGRATION_TEST_PASSWORD;

	@BeforeTestClass
	@FlywayTest
	public static void beforeClass() {

	}
}
