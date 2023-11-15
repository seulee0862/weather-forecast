package com.project02server.oauth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class KakaoTokenDto {

	@Builder @Getter
	public static class Request {
		private String grant_type;
		private String client_id;
		private String redirect_uri;
		private String code;
		private String client_secret;
	}

	@NoArgsConstructor
	@Getter
	public static class Response {
		private String token_type;
		private String access_token;
		private Integer expires_in;
		private String refresh_token;
		private Integer refresh_token_expires_in;
		private String scope;
		private String id_token;

		@Builder
		public Response(String token_type, String access_token, Integer expires_in, String refresh_token,
			Integer refresh_token_expires_in, String scope) {
			this.token_type = token_type;
			this.access_token = access_token;
			this.expires_in = expires_in;
			this.refresh_token = refresh_token;
			this.refresh_token_expires_in = refresh_token_expires_in;
			this.scope = scope;
		}
	}
}
