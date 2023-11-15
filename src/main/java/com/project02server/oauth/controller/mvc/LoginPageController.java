package com.project02server.oauth.controller.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project02server.oauth.dto.KakaoTokenDto;
import com.project02server.oauth.dto.request.OidcLoginRequest;
import com.project02server.oauth.service.OAuthService;
import com.project02server.oauth.service.kakao.KakaoTokenClient;

@Controller
public class LoginPageController {

	private final OAuthService oAuthService;
	private final KakaoTokenClient kakaoTokenClient;
	private final String clientId;
	private final String clientSecret;

	public LoginPageController(
		KakaoTokenClient kakaoTokenClient,
		@Value("${kakao.oidc.aud.rest}") String clientId,
		@Value("${kakao.secret}") String clientSecret,
		OAuthService oAuthService) {
		this.kakaoTokenClient = kakaoTokenClient;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.oAuthService = oAuthService;
	}

	@GetMapping("/oauth/kakao/callback")
	public @ResponseBody ResponseEntity<String> loginCallback(String code) {

		String contentType = "application/x-www-form-urlencoded;charset=utf-8";
		KakaoTokenDto.Request kakaoTokenRequestDto = KakaoTokenDto.Request.builder()
			.client_id(clientId)
			.client_secret(clientSecret)
			.grant_type("authorization_code")
			.code(code)
			.redirect_uri("http://localhost:8080/oauth/kakao/callback")
			.build();

		KakaoTokenDto.Response response = kakaoTokenClient.requestKakaoToken(contentType, kakaoTokenRequestDto);
		OidcLoginRequest oidcLoginRequest = OidcLoginRequest.KakaoLoginRequest(response.getId_token());

		return ResponseEntity.ok
			(oAuthService.oidcLogin(oidcLoginRequest, "kakao"));
	}

	@GetMapping("/login")
	public String login() {
		return "loginForm";
	}
}
