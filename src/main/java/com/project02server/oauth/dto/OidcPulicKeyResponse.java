package com.project02server.oauth.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class OidcPulicKeyResponse {

	private List<OidcPublicKeyDto> keys;
}
