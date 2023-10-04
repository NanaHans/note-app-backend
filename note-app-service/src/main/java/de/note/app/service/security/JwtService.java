package de.note.app.service.security;

import jakarta.servlet.http.HttpServletRequest;

public interface JwtService {

	String generateJwt(Long userId, String username);

	String extractTokenFromRequest(HttpServletRequest request);

	Long getUserId(String token);

	String getUsername(String token);

	boolean validateToken(String token);
}
