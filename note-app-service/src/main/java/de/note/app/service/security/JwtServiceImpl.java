package de.note.app.service.security;

import de.note.app.service.services.common.error.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

/**
 * 
 * @author ${Arsen Nana}
 *
 */
@Service
public class JwtServiceImpl implements JwtService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtServiceImpl.class);

	private static final String AUTH_HEADER_KEY = "Authorization";
	private static final String AUTH_HEADER_VALUE_PREFIX = "Bearer ";
	private static final String CLAIMS_USER_ID = "userId";
	private static final String JWT_ISSUER = "note-app-backend";
	private static final int EXPIRATION_HOURS = 8;
	private static final String CLAIMS_USERNAME = "username";

	@Value("${note.app.jwt.shared.key}")
	private String noteAppJwtSharedKey;

	/**
	 * generates a JWT-token for a given user
	 */
	@Override
	public String generateJwt(Long userId, String username) {
		Map<String, Object> claims = new HashMap<>();

		claims.put(CLAIMS_USER_ID, userId);
		claims.put(CLAIMS_USERNAME, username);
		Date now = new Date();
		Calendar expirationDate = Calendar.getInstance();
		expirationDate.add(Calendar.HOUR, EXPIRATION_HOURS);

		SecretKey key = Keys.hmacShaKeyFor(noteAppJwtSharedKey.getBytes());

		return Jwts.builder().setClaims(claims).signWith(key).setIssuer(JWT_ISSUER).setNotBefore(now).setIssuedAt(now)
				.setId(UUID.randomUUID().toString()).compact();
	}

	/**
	 * extract jwt-token from {@link HttpServletRequest}
	 */
	@Override
	public String extractTokenFromRequest(HttpServletRequest request) {
		// extract auth header
		final String authHeader = request.getHeader(AUTH_HEADER_KEY);

		// check if token is available
		if (authHeader == null || !authHeader.toLowerCase().startsWith(AUTH_HEADER_VALUE_PREFIX.toLowerCase())) {
			throw new UnauthorizedException(new Throwable("No valid authentication autheader, no jwt token was found"));
		}
		String token = authHeader.substring(AUTH_HEADER_VALUE_PREFIX.length());
		LOGGER.debug("extracted token: {}", token);
		return token;
	}

	/**
	 * return user-id from jwt-token
	 */
	@Override
	public Long getUserId(String token) {
		try {
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(noteAppJwtSharedKey.getBytes()).build()
					.parseClaimsJws(token);
			return ((Integer) claims.getBody().get(CLAIMS_USER_ID)).longValue();
		} catch (Exception e) {
			LOGGER.error("failed to get userId from token: {} ", token);
			return 0L;

		}
	}

	@Override
	public String getUsername(String token) {
		try {
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(noteAppJwtSharedKey.getBytes()).build()
					.parseClaimsJws(token);
			return (String) claims.getBody().get(CLAIMS_USERNAME);
		} catch (Exception e) {
			LOGGER.error("failed to get userId from token: {} ", token);
			return "";

		}
	}

	@Override
	public boolean validateToken(String token) {
		Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(noteAppJwtSharedKey.getBytes()).build()
				.parseClaimsJws(token);
		return !claims.getBody().isEmpty();
	}

}
