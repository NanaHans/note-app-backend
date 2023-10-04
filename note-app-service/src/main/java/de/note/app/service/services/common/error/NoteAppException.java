/**
 * 
 */
package de.note.app.service.services.common.error;

import java.io.Serial;

/**
 * @author ${Arsen Nana}
 *
 */
public class NoteAppException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * @param errorCode {@link {@link NoteAppErrorCode}}
	 * @author arsen
	 * 
	 */
	public NoteAppException(NoteAppErrorCode errorCode) {
		super(errorCode.getErrorMessage());
	}

	/**
	 * 
	 * @param errorCode {@link NoteAppErrorCode}
	 * @param throwable {@link Throwable}
	 * @author arsen
	 */
	public NoteAppException(NoteAppErrorCode errorCode, Throwable throwable) {
		super(errorCode.getErrorMessage(), throwable);
	}

}
