package com.project02server.user.dto.request;

import lombok.Getter;

@Getter
public class SubscribeReqeust {

	private final String regionName;

	public SubscribeReqeust(String regionName) {
		this.regionName = regionName;
	}
}
