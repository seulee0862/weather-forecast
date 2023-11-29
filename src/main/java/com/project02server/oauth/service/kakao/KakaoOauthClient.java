package com.project02server.oauth.service.kakao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.project02server.common.config.open_feign.OpenFeignConfig;
import com.project02server.oauth.dto.OidcPulicKeyResponse;

@FeignClient(
	name = "KakaoOauthClient",
	url = "https://kauth.kakao.com",
	configuration = OpenFeignConfig.class
)
public interface KakaoOauthClient {

	@Cacheable(value = "kakaoPublicKey")
	@GetMapping("/.well-known/jwks.json")
	OidcPulicKeyResponse getKakaoOidcOpenKeys();
}
