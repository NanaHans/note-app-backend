/**
 * 
 */
package de.note.app.io.services.common.error;

/**
 * @author ${Arsen Nana}
 *
 */
public class NoteAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private NoteAppErrorCode errorCode;

	/**
	 * @param errorCode {@link {@link NoteAppErrorCode}}
	 * @author arsen
	 * 
	 */
	public NoteAppException(NoteAppErrorCode errorCode) {
		super(errorCode.getErrorMessage());
		this.errorCode = errorCode;
	}

	/**
	 * 
	 * @param errorCode {@link NoteAppErrorCode}
	 * @param throwable {@link Throwable}
	 * @author arsen
	 */
	public NoteAppException(NoteAppErrorCode errorCode, Throwable throwable) {
		super(errorCode.getErrorMessage(), throwable);
		this.errorCode = errorCode;
	}

}
