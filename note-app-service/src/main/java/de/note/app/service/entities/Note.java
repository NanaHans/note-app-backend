package de.note.app.service.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "note")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Note implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	@Column
	private String title;
	@Column
	private String body;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Note(String title, String body, User user) {
		this.title = title;
		this.body = body;
		this.user = user;
	}
}
