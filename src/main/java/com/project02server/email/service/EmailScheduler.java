package com.project02server.email.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project02server.user.domain.User;
import com.project02server.user.dto.UserDto;
import com.project02server.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailScheduler {

	private final UserService userService;
	private final EmailManager emailManager;

	@Scheduled(cron = "0 0 20 * * *")
	public void sendWeatheToUser() {
		List<User> users = userService.findUsersByActiveSubscribe();

		List<UserDto> userDtos = users.stream()
			.map(user -> UserDto.from(user))
			.toList();

		userDtos.forEach(userDto -> emailManager.sendEmail(userDto));
	}
}