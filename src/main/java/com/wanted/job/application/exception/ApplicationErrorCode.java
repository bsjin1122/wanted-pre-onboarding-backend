package com.wanted.job.application.exception;

import org.springframework.http.HttpStatus;

import com.wanted.job.common.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplicationErrorCode implements ErrorCode {
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),

	ALREADY_APPLIED(HttpStatus.ALREADY_REPORTED, "사용자가 이미 채용공고에 지원했습니다.")	;

	private final HttpStatus status;
	private final String message;
}
