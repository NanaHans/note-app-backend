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

	private String message;

	public MessageResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
