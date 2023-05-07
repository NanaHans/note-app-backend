/**
 * 
 */
package de.note.app.io.enums;

/**
 * @author ${Arsen Nana}
 *
 */
public enum ResponseMessage {
	REGISTER_USERNAME(1, "Username is already taken!"), REGISTER_EMAIL(2, "Email is already in use!"),
	REGISTER_SUCCESS(3, "User registered successfully!");

	private final int id;
	private final String message;

	private ResponseMessage(int id, String message) {
		this.id = id;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

}
