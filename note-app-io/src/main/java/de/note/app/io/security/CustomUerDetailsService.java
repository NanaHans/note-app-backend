/**
 * 
 */
package de.note.app.io.security;

import de.note.app.io.dao.UserRepository;
import de.note.app.io.entity.User;
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
