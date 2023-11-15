package com.project02server.oauth.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project02server.common.code.ErrorCode;
import com.project02server.common.exception.customException.CustomAuthenticationException;
import com.project02server.oauth.dto.IdTokenAttributes;
import com.project02server.oauth.dto.request.OidcLoginRequest;
import com.project02server.oauth.util.JwtUtil;
import com.project02server.user.domain.User;
import com.project02server.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuthService {

	private final UserService userService;
	private final Map<String, OidcLoginApiService> providerMap;

	@Transactional
	public String oidcLogin(OidcLoginRequest loginRequest, String oAuthProvider) {

		OidcLoginApiService oidcLoginService = providerMap.get(oAuthProvider.toLowerCase());
		if (oidcLoginService == null) {
			throw  new CustomAuthenticationException(ErrorCode.NOT_SUPPORTED_OAUTH_PROVIDER);
		}

		IdTokenAttributes idTokenAttributes = oidcLoginService.verify
			(loginRequest.getToken(), loginRequest.getPlatform().toString());

		User user = userService.findByEmailAndPlatform(idTokenAttributes.getEmail(), oAuthProvider)
			.orElseGet(() -> userService.signUp(idTokenAttributes.getEmail(), oAuthProvider));

		return JwtUtil.createJwt(user.getId());
	}

}
