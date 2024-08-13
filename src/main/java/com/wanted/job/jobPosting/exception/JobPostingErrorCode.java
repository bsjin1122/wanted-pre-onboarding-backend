package com.wanted.job.jobPosting.exception;

import org.springframework.http.HttpStatus;

import com.wanted.job.common.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JobPostingErrorCode implements ErrorCode {
	JOB_POSTING_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 채용 공고입니다.");

	private final HttpStatus status;
	private final String message;
}
