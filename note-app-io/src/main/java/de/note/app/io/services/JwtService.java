package de.note.app.io.services;

import javax.servlet.http.HttpServletRequest;

public interface JwtService {

	String generateJwt(Long userId);

	String extractTokenFromRequest(HttpServletRequest request);

	Long getUserId(String token);
}
