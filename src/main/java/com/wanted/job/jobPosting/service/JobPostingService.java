package com.wanted.job.jobPosting.service;

import com.wanted.job.jobPosting.model.dto.JobPostingRequestDTO;

public interface JobPostingService {
	void registerJobPosting(JobPostingRequestDTO request);
}
