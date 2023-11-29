package com.project02server.email.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.project02server.email.dto.EmailMessage;
import com.project02server.user.dto.UserDto;
import com.project02server.weather.domain.Weather;
import com.project02server.weather.dto.WeatherDto;
import com.project02server.weather.service.WeatherService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailManager {

	private final WeatherService weatherService;
	private final JavaMailSender javaMailSender;
	private final SpringTemplateEngine templateEngine;
	private final static String EMAIL = "email";
	
	public void sendEmail(UserDto userDto) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		EmailMessage emailMessage = createEmailMessage(userDto.getEmail());

		try {
			List<WeatherDto> weatherDtos = getWeatherWithin24Hours(userDto.getRegionName(), getMidnight());
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			mimeMessageHelper.setTo(emailMessage.getTo()); // 메일 수신자
			mimeMessageHelper.setSubject(emailMessage.getSubject()); // 메일 제목
			mimeMessageHelper.setText(setContext(weatherDtos, EMAIL), true); // 메일 본문 내용, HTML 여부
			javaMailSender.send(mimeMessage);
			log.info("mail send success");

		} catch (MessagingException e) {
			log.info("mail send fail");
			throw new RuntimeException(e);
		}
	}

	/**
	 * 이메일 발송에 필요한 일기예보 데이터 캐싱
	 */
	@Cacheable(value = "weatherWithin24Hours", key = "#regionName.concat('-').concat(#startDateTime.toString())")
	public List<WeatherDto> getWeatherWithin24Hours(String regionName, LocalDateTime startDateTime) {
		List<Weather> weathers = weatherService.findWeatherWithin24Hours(regionName, startDateTime);

		return weathers
			.stream()
			.map(WeatherDto::from)
			.toList();
	}


	private static LocalDateTime getMidnight() {
		return LocalDateTime.now(ZoneId.of("Asia/Seoul"))
			.withHour(0)
			.withMinute(0)
			.withSecond(0)
			.truncatedTo(ChronoUnit.SECONDS);
	}

	public EmailMessage createEmailMessage(String receiverEmail) {
		return EmailMessage.builder()
			.to(receiverEmail)
			.subject("\"일기예보 전달을 위한 메일 발송\"")
			.build();
	}

	private String setContext(List<WeatherDto> weathers, String type) {
		Context context = new Context();
		context.setVariable("weathers", weathers);

		return templateEngine.process(type, context);
	}
}
