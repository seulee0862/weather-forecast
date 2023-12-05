package com.project02server.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project02server.user.dto.request.SubscribeReqeust;
import com.project02server.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@PatchMapping("/subscribe")
	public ResponseEntity subscribe(HttpServletRequest httpServletRequest, @RequestBody SubscribeReqeust reqeust) {

		Long userId = (Long)httpServletRequest.getAttribute("userId");
		userService.subscribeToWeather(userId, reqeust);

		return ResponseEntity.ok().build();
	}

	@PatchMapping("/unsubscribe")
	public ResponseEntity unsubscribe(HttpServletRequest httpServletRequest) {
		Long userId = (Long)httpServletRequest.getAttribute("userId");
		userService.unsubscribeFromWeather(userId);

		return ResponseEntity.ok().build();
	}

}