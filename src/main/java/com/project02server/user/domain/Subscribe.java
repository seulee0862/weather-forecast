package com.project02server.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Subscribe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String regionName;
	private boolean isSubscribed;

	public void activeSubscription(String regionName) {
		this.regionName = regionName;
		this.isSubscribed = true;
	}

	public void deactivateSubscription() {
		isSubscribed = false;
	}

	public static Subscribe defaultOption() {
		return Subscribe.builder()
			.isSubscribed(false)
			.build();
	}

	@Builder
	public Subscribe(String regionName, boolean isSubscribed) {
		this.regionName = regionName;
		this.isSubscribed = isSubscribed;
	}
}
