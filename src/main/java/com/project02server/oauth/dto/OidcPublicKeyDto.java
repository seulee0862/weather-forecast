package com.project02server.oauth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class OidcPublicKeyDto {

	private String kid;
	private String alg;
	private String use;
	private String n;
	private String e;
}
