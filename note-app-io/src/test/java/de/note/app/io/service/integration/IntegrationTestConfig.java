/**
 * 
 */
package de.note.app.io.service.integration;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;

/**
 * @author ${Arsen Nana}
 *
 */
@Order(HIGHEST_PRECEDENCE)
@TestConfiguration
@ComponentScan(basePackages = "de.note.io.services.")
public class IntegrationTestConfig {

	@Autowired
	private Environment env;

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		return mapper;
	}

	@Bean
	public MariaDB4jSpringService mariaDB4jSpringService() {
		return new MariaDB4jSpringService();
	}

	@Bean
	public DataSource dataSource() throws ManagedProcessException {
		MariaDB4jSpringService mariaDB4jSpringService = mariaDB4jSpringService();
		mariaDB4jSpringService.getDB().createDB(env.getProperty("app.mariaDB4j.databaseName"));
		DBConfigurationBuilder config = mariaDB4jSpringService.getConfiguration();

		return DataSourceBuilder.create().username(env.getProperty("spring.datasource.username"))
				.password(env.getProperty("spring.datasource.password"))
				.url(config.getURL(env.getProperty("app.mariaDB4j.databaseName")))
				.driverClassName(env.getProperty("spring.datasource.driver-class-name")).build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ManagedProcessException {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("de.note.io.entity");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaProperties(additionalProperties());
		return em;
	}

	private Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

		return hibernateProperties;
	}

	@Bean
	public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public Flyway flyway() throws ManagedProcessException {
		return Flyway.configure().dataSource(dataSource()).locations("classpath:db/migration").load();
	}

}
