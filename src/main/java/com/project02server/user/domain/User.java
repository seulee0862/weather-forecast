package com.project02server.user.domain;

import com.project02server.common.domain.BaseTimeEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String email;
	@NotNull
	private String oAuthProvider;

	@NotNull
	@OneToOne(orphanRemoval = true)
	@JoinColumn(name = "weather_subscription_id")
	private Subscribe subscribe;

	public static User of (String email, String oAuthProvider, Subscribe subscribe) {
		return User.builder()
			.email(email)
			.oAuthProvider(oAuthProvider)
			.subscribe(subscribe)
			.build();
	}

	@Builder
	public User(String email, String oAuthProvider, Subscribe subscribe) {
		this.email = email;
		this.oAuthProvider = oAuthProvider;
		this.subscribe = subscribe;
	}
}
