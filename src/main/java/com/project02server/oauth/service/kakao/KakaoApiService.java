package com.project02server.oauth.service.kakao;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project02server.oauth.dto.IdTokenAttributes;
import com.project02server.oauth.service.OidcLoginApiService;
import com.project02server.oauth.service.OidcService;

@Service("kakao")
public class KakaoApiService implements OidcLoginApiService {

	private final OidcService oidcService;
	private final KakaoOauthClient  kakaoOauthClient;
	private final String iss;

	private final Map<String, String> kakaoAudMap;

	public KakaoApiService(
		OidcService oidcService,
		KakaoOauthClient kakaoOauthClient,
		@Value("${kakao.oidc.iss}") String iss,
		@Value("${kakao.oidc.aud.web}") String webAud,
		@Value("${kakao.oidc.aud.ios}") String iosAud,
		@Value("${kakao.oidc.aud.aos}") String aosAud,
		@Value("${kakao.oidc.aud.rest}") String restAud) {
		this.oidcService = oidcService;
		this.kakaoOauthClient = kakaoOauthClient;
		this.iss = iss;
		Map<String, String> tempMap = new HashMap<>();
		tempMap.put("web", webAud);
		tempMap.put("ios", iosAud);
		tempMap.put("aos", aosAud);
		tempMap.put("rest", restAud);
		this.kakaoAudMap = Collections.unmodifiableMap(tempMap);
	}

	@Override
	public IdTokenAttributes verify(String token, String platform) {
		String aud = kakaoAudMap.get(platform.toLowerCase());

		return oidcService.getPayloadFromIdToken(token, iss, aud,
			kakaoOauthClient.getKakaoOidcOpenKeys());
	}
}
