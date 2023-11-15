package com.project02server.common.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	//400에러
	NOT_SUPPORTED_OAUTH_PROVIDER(HttpStatus.BAD_REQUEST, "A-008", "지원하지 않는 OAuth provider 입니다."),

	//401에러
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-001", "토큰이 만료되었습니다."),
	NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-002", "해당 토큰은 유효한 토큰이 아닙니다."),
	MALFORMED_JWT(HttpStatus.UNAUTHORIZED, "A-003", "올바른 JWT 형식이 아닙니다."),
	NOT_SUPPORTED_JWT(HttpStatus.UNAUTHORIZED, "A-004", "지원되지 않는 JWS 입니다."),
	INVALID_PAYLOAD(HttpStatus.UNAUTHORIZED, "A-005", "페이로드 검증에 실패했습니다."),
	JWT_VERIFY_FAILED(HttpStatus.UNAUTHORIZED, "A-006", "JWT 시그니처가 올바르지 않습니다."),
	NOT_ACCESS_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "A-007", "해당 토큰은 ACCESS TOKEN이 아닙니다."),

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
