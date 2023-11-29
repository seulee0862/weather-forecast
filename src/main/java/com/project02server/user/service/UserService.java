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
		Subscribe subscribe = subscribeService.createDefaultSubscription();
		return userRepository.save(User.of(email, oAuthProvider, subscribe));
	}

	public User findById(Long id) {
		return userRepository.findById(id)
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_ENTITY));
	}

	private Optional<User> getByEmailAndPlatform(String email, String oAuthProvider) {
		return qUserRepository.findByEmailAndPlatform(email, oAuthProvider);
	}

	@Transactional
	public void subscribeToWeather(Long userId, SubscribeReqeust reqeust) {
		User user = findById(userId);

		if (!coordinateService.existByRegionName(reqeust.getRegionName())) {
			throw new BusinessException(ErrorCode.INVALID_REGION_NAME);
		}

		user.getSubscribe().activeSubscription(reqeust.getRegionName());
	}

	@Transactional
	public void unsubscribeFromWeather(Long userId) {
		User user = findById(userId);

		user.getSubscribe().deactivateSubscription();
	}

	public List<User> findUsersByActiveSubscribe() {
		return userRepository.findUsersByActiveSubscribe();
	}
}
