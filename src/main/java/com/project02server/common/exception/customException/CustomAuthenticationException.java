package com.project02server.common.exception.customException;

import com.project02server.common.code.ErrorCode;

public class CustomAuthenticationException extends BusinessException{

	private RuntimeException originalException;

	public CustomAuthenticationException(ErrorCode errorCode) {
		super(errorCode);
	}

	public CustomAuthenticationException(ErrorCode errorCode, RuntimeException e) {
		super(errorCode);
		this.originalException = e;
	}

	public RuntimeException getOriginalException() {
		return originalException == null
			? originalException
			: this;
	}
}
