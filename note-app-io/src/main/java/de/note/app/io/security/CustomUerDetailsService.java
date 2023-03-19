/**
 * 
 */
package de.note.app.io.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.note.app.io.dao.UserRepository;
import de.note.app.io.entity.User;

/**
 * loads user specific data
 * 
 * @author ${Arsen Nana}
 *
 */
@Service
public class CustomUerDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepos;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepos.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				Collections.emptyList());
	}

}
