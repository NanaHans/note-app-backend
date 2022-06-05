/**
 * 
 */
package de.note.app.io.services.common.error.exception;

import de.note.app.io.services.common.error.NoteAppErrorCode;
import de.note.app.io.services.common.error.NoteAppException;

/**
 * @author ${Arsen Nana}
 *
 */
public class WrongUsernameOrPasswordException extends NoteAppException {

	private static final long serialVersionUID = 1L;

	public WrongUsernameOrPasswordException() {
		super(NoteAppErrorCode.WROG_USERNAME_OR_PASSWORD_ERROR);
	}

}
