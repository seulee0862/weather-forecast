package com.project02server.oauth.service;

import org.springframework.stereotype.Service;

import com.project02server.oauth.dto.IdTokenAttributes;
import com.project02server.oauth.dto.OidcPublicKeyDto;
import com.project02server.oauth.dto.OidcPulicKeyResponse;
import com.project02server.oauth.util.OAuthJwtManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OidcService {

	private final OAuthJwtManager oAuthJwtManager;

	private String getKidFromUnsignedIdToken(String token, String iss, String aud) {
		return oAuthJwtManager.getKidFromUnsignedTokenHeader(token, iss, aud);
	}

	public IdTokenAttributes getPayloadFromIdToken(
		String token, String iss, String aud, OidcPulicKeyResponse oidcPulicKeyResponse) {

		String kid = getKidFromUnsignedIdToken(token, iss, aud);

		OidcPublicKeyDto oidcPublicKeyDto = oidcPulicKeyResponse.getKeys()
			.stream()
			.filter(o -> o.getKid().equals(kid))
			.findFirst()
			.orElseThrow();

		return oAuthJwtManager.getOIDCTokenBody(
			token,
			oidcPublicKeyDto.getN(),
			oidcPublicKeyDto.getE()
		);
	}
}
