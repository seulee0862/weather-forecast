package com.project02server.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project02server.user.domain.Subscribe;
import com.project02server.user.repository.SubscribeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRepository;

	@Transactional
	public Subscribe createDefaultSubscription() {
		return subscribeRepository.save(Subscribe.defaultOption());
	}
}
