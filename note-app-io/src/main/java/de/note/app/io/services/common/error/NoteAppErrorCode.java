package de.note.app.io.services.common.error;

import org.springframework.http.HttpStatus;

public enum NoteAppErrorCode {

	WROG_USERNAME_OR_PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "Wrong username or password");

	private HttpStatus httpStatus;
	private String errorMessage;

	private NoteAppErrorCode(HttpStatus httpStatus, String errorMessage) {
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
