package com.project02server.common.exception.customException;

import com.project02server.common.code.ErrorCode;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private final ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public BusinessException(String message) {
		super(message);
		this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
	}
}


