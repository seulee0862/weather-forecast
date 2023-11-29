package com.project02server.email.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project02server.email.dto.EmailPostDto;
import com.project02server.email.service.EmailManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/send-mail")
public class EmailController {

	private final EmailManager emailManager;

	@PostMapping("/email")
	public ResponseEntity sendMail(@RequestBody EmailPostDto emailPostDto) {
		// EmailMessage emailMessage = EmailMessage.builder()
		// 	.to(emailPostDto.getEmail())
		// 	.subject("\"일기예보 전달을 위한 메일 발송\"")
		// 	.build();
		//
		// emailManager.sendEmail(emailMessage);

		return ResponseEntity
			.ok()
			.build();
	}
}
