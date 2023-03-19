package de.note.app.io.security;

import javax.servlet.http.HttpServletRequest;

public interface JwtService {

	String generateJwt(Long userId, String username);

	String extractTokenFromRequest(HttpServletRequest request);

	Long getUserId(String token);

	String getUsername(String token);

	boolean validateToken(String token);
}
