package com.project02server.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String email;

	@NotNull
	private String oAuthProvider;

	public static User of (String email, String oAuthProvider) {
		return User.builder()
			.email(email)
			.oAuthProvider(oAuthProvider)
			.build();
	}

	@Builder
	public User(String email, String oAuthProvider) {
		this.email = email;
		this.oAuthProvider = oAuthProvider;
	}
}
