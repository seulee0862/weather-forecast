package com.project02server.oauth.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

	private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public static String createJwt(Long userId) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", userId);

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1시간 유효
			.signWith(SECRET_KEY)
			.compact();
	}

	public static Long parseAndVerifyJwt(String jwt) {
		try {
			Claims claims = Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(jwt)
				.getBody();

			return (Long) claims.get("userId", Long.class);
		} catch (Exception e) {
			// JWT 검증 실패 처리
			throw new RuntimeException("JWT verification failed", e);
		}
	}
}
