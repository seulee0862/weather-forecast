package com.project02server.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project02server.user.domain.User;
import com.project02server.user.repository.QUserRepository;
import com.project02server.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final QUserRepository qUserRepository;
	private final UserRepository userRepository;

	public Optional<User> findByEmailAndPlatform(String email, String oAuthProvider) {
		return getByEmailAndPlatform(email, oAuthProvider);
	}

	public User signUp(String email, String oAuthProvider){
		return save(email, oAuthProvider);
	}

	private User save(String email, String oAuthProvider) {
		return userRepository.save(User.of(email, oAuthProvider));
	}

	private Optional<User> getByEmailAndPlatform(String email, String oAuthProvider) {
		return qUserRepository.findByEmailAndPlatform(email, oAuthProvider);
	}

}
