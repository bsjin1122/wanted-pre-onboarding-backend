package com.wanted.job.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class BaseException extends RuntimeException{
	private final ErrorCode errorCode;

	public BaseException(ErrorCode errorCode) {
		super(errorCode.getMessage());  // 메시지를 부모 클래스에 전달
		this.errorCode = errorCode;
	}
}
