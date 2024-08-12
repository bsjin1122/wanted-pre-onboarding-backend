package com.wanted.job.company.exception;

import com.wanted.job.common.exception.BaseException;

public class CompanyException extends BaseException {
	public CompanyException(CompanyErrorCode errorCode){
			super(errorCode);
	}
}
