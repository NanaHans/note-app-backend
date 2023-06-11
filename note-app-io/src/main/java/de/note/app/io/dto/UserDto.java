package de.note.app.io.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private  Long id;
	private  @NonNull String email;
	private  @NonNull String password;
	private  @NonNull String firstname;
	private  @NonNull String lastname;
	private  @NonNull String username;

}
