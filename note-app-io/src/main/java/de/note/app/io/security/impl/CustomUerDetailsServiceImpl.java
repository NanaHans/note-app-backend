/**
 * 
 */
package de.note.app.io.security.impl;

import de.note.app.io.dao.UserRepository;
import de.note.app.io.entity.User;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CustomUerDetailsServiceImpl implements UserDetailsService {


	private final UserRepository userRepos;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepos.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				Collections.emptyList());
	}

}
