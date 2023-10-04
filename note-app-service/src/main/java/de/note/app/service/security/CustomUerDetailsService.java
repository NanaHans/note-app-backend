/**
 * 
 */
package de.note.app.service.security;
import de.note.app.service.entities.User;
import de.note.app.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * loads user specific data
 * 
 * @author ${Arsen Nana}
 *
 */
@Service
public class CustomUerDetailsService implements UserDetailsService {


	private final UserRepository userRepos;
	@Autowired
	public CustomUerDetailsService(UserRepository userRepos) {
		this.userRepos = userRepos;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepos.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				Collections.emptyList());
	}

}
