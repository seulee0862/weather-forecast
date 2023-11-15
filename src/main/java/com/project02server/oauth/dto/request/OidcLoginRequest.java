package com.project02server.oauth.dto.request;

import com.project02server.common.code.Platform;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OidcLoginRequest {

	@NotBlank
	private String token;

	@NotNull
	Platform platform;

	public static OidcLoginRequest KakaoLoginRequest(String jwt) {
		return new OidcLoginRequest(jwt, Platform.REST);
	}

	private OidcLoginRequest(String token, Platform platform) {
		this.token = token;
		this.platform = platform;
	}
}
