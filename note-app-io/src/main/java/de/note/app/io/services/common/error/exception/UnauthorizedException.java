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
public class UnauthorizedException extends NoteAppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UnauthorizedException(Throwable throwable) {
		super(NoteAppErrorCode.UNAUTHORIZED, throwable);
	}

}
