package de.note.app.io.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "note")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Note implements Serializable {
	@Serial
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
