/**
 * 
 */
package de.note.app.service.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ${Arsen Nana}
 *
 */
public class CustomUerDetailsDto {
	private String username;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;

	/**
	 * 
	 */
	public CustomUerDetailsDto(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.active = user.isEnabled();
		this.authorities = new ArrayList<>(user.getAuthorities());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}
