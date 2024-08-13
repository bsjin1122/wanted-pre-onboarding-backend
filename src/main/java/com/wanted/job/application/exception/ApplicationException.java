package com.wanted.job.application.exception;

import com.wanted.job.common.exception.BaseException;
import com.wanted.job.jobPosting.exception.JobPostingErrorCode;

public class ApplicationException extends BaseException {
	public ApplicationException(ApplicationErrorCode errorCode){
		super(errorCode);
	}

}
