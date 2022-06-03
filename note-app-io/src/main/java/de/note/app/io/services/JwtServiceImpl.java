package de.note.app.io.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtServiceImpl.class);

	private static final String AUTH_HEADER_KEY = "authorization";
	private static final String AUTH_HEADER_VALUE_PREFIX = "Bear";

	private static final String CLAIMS_USER_ID = "userId";

	private static final String JWT_SUBJECT = "USER";
	private static final String JWT_ISSUER = "note-app-backend";
	private static final String JWT_AUDIENCE = "note-app";
	private static final int EXPIRATION_HOURS = 8;

	@Value("${note.app.jwt.shared.key}")
	private String noteAppJwtSharedKey;

	@Override
	public String generateJwt(Long userId) {
		Map<String, Object> claims = new HashMap<>();

		claims.put(CLAIMS_USER_ID, userId);
		Date now = new Date();
		Calendar expirationDate = Calendar.getInstance();
		expirationDate.add(Calendar.HOUR, EXPIRATION_HOURS);

		SecretKey key = Keys.hmacShaKeyFor(noteAppJwtSharedKey.getBytes());

		return Jwts.builder().setClaims(claims).signWith(key).setIssuer(JWT_ISSUER).setNotBefore(now).setIssuedAt(now)
				.setId(UUID.randomUUID().toString()).compact();
	}

	@Override
	public String extractTokenFromRequest(HttpServletRequest request) {
		// extract auth header
		final String authHeader = request.getHeader(AUTH_HEADER_KEY);

		// check if token s available
		if (authHeader == null || !authHeader.toLowerCase().startsWith(AUTH_HEADER_VALUE_PREFIX.toLowerCase())) {
			LOGGER.error("No valid authentication autheader, no jwt token was found");
		}
		String token = authHeader.substring(AUTH_HEADER_VALUE_PREFIX.length());
		LOGGER.debug("extracted token: {}", token);
		return null;
	}

	@Override
	public Long getUserId(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.noteAppJwtSharedKey.getBytes()).parseClaimsJws(token);
			return ((Integer) claims.getBody().get(CLAIMS_USER_ID)).longValue();
		} catch (Exception e) {
			LOGGER.error("failled to get UserId from token => ", token);
		}
		return null;
	}

}
