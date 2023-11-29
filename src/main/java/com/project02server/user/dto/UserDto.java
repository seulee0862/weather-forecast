package com.project02server.user.dto;

import com.project02server.user.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {

	private String email;
	private String oAuthProvider;
	private String regionName;
	private boolean isSubscribed;

	public static UserDto from(User user) {
		return UserDto.builder()
			.email(user.getEmail())
			.oAuthProvider(user.getOAuthProvider())
			.regionName(user.getSubscribe().getRegionName())
			.isSubscribed(user.getSubscribe().isSubscribed())
			.build();
	}

	@Builder
	private UserDto(String email, String oAuthProvider, String regionName, boolean isSubscribed) {
		this.email = email;
		this.oAuthProvider = oAuthProvider;
		this.regionName = regionName;
		this.isSubscribed = isSubscribed;
	}
}
