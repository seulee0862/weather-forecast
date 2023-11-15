package com.project02server.oauth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project02server.oauth.dto.request.OidcLoginRequest;
import com.project02server.oauth.service.OAuthService;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class OAuthController {

	private final OAuthService oAuthService;

	@PostMapping("/oauth/login/{provider}")
	public ResponseEntity<String> oidcLogin(@NotBlank @PathVariable("provider") String oAuthProvider,
		@RequestBody OidcLoginRequest oidcLoginRequest) {

		String jwt = oAuthService.oidcLogin(oidcLoginRequest, oAuthProvider);
		return ResponseEntity.ok()
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
			.build();
	}
}
