/**
 * 
 */
package de.note.app.io.services.common.message;

import java.io.Serializable;

/**
 * @author ${Arsen Nana}
 *
 */
public class MessageResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int messageId;
	private String message;

	public MessageResponse(int id, String message) {
		this.messageId = id;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return messageId;
	}

	public void setId(int id) {
		this.messageId = id;
	}

}
