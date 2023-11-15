package com.project02server.oauth.dto;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class IdTokenAttributes {

	public String email;
	public String name;
	public String profile;

	public IdTokenAttributes(Claims claims) {
		this.email = (String)claims.get("email");
		this.name = (String)claims.get("nickname");
		this.profile = (String)claims.get("profile");
	}
}
