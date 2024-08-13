package com.wanted.job.jobPosting.service;

import com.wanted.job.jobPosting.model.dto.JobPostingRequestDTO;
import com.wanted.job.jobPosting.model.dto.JobPostingResponseDTO;
import com.wanted.job.jobPosting.model.entity.JobPosting;

public interface JobPostingService {
	void registerJobPosting(JobPostingRequestDTO request);

	void deleteJobPosting(Long jobPostingId);

	void updateJobPosting(Long jobPostingId, JobPostingRequestDTO request);
	JobPostingResponseDTO detailJobPosting(Long jobPostingId);
}
