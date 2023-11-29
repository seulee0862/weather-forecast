package com.project02server.oauth.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.project02server.common.code.ErrorCode;
import com.project02server.common.exception.customException.BusinessException;
import com.project02server.common.exception.customException.CustomAuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
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

	public static Claims parseAndVerifyJwt(String jwt) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(jwt)
				.getBody();

		} catch (InvalidClaimException e) {
			throw new CustomAuthenticationException(ErrorCode.INVALID_PAYLOAD, e);
		}catch (ExpiredJwtException e) {
			throw new CustomAuthenticationException(ErrorCode.TOKEN_EXPIRED, e);
		}catch (UnsupportedJwtException e) {
			throw new CustomAuthenticationException(ErrorCode.NOT_SUPPORTED_JWT, e);
		}catch (MalformedJwtException e) {
			throw new CustomAuthenticationException(ErrorCode.MALFORMED_JWT, e);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public static Long getUserId(Claims claims)
	{
		return (Long)claims.get("userId", Long.class);
	}
}
