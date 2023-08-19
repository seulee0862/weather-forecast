package com.project02server.common.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	//404에러
	NOT_FOUND_ENTITY(HttpStatus.NOT_FOUND, "E-001", "존재하지 않는 Entity 입니다."),

	//429에러
	OPENAPI_CALLS_OVER_LIMIT(HttpStatus.TOO_MANY_REQUESTS, "E-002", "허용된 API 호출 횟수를 초과했습니다."),

	//500에러
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "I-001", "내부 에러가 발생했습니다.")
	;

	ErrorCode(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
