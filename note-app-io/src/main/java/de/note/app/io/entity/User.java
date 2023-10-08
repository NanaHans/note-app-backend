package de.note.app.io.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String email;
	@Column(name = "password")
	private String password;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String username;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Note> notes = new ArrayList<>();

	public User(String email, String password, String firstname, String lastname, String username) {
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
	}



}
