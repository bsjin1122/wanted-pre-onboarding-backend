package com.wanted.job.jobPosting.exception;

import com.wanted.job.common.exception.BaseException;

public class JobPostingException extends BaseException {
	public JobPostingException(JobPostingErrorCode errorCode){
		super(errorCode);
	}
}
