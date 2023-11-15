package com.project02server.oauth.service;

import com.project02server.oauth.dto.IdTokenAttributes;

public interface OidcLoginApiService {

	IdTokenAttributes verify(String token, String platform);

}
