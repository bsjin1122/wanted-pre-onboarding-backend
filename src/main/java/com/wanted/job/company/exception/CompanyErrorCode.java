package com.wanted.job.company.exception;

import org.springframework.http.HttpStatus;

import com.wanted.job.common.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanyErrorCode implements ErrorCode {
	COMPANY_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회사 ID 입니다.");

	private final HttpStatus status;
	private final String message;

}
