/**
 * 
 */
package de.note.app.io.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ${Arsen Nana}
 *
 */
@Configuration
public class UtilityConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
