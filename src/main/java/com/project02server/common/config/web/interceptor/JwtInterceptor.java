package com.project02server.common.config.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.project02server.common.code.ErrorCode;
import com.project02server.common.exception.customException.CustomAuthenticationException;
import com.project02server.oauth.util.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new CustomAuthenticationException(ErrorCode.AUTHENCATION_HEAHER_NOT_FOUND);
		}

		String token = authHeader.substring("bearer ".length());
		Claims claims = JwtUtil.parseAndVerifyJwt(token);
		request.setAttribute("userId", JwtUtil.getUserId(claims));

		return true;
	}
}
