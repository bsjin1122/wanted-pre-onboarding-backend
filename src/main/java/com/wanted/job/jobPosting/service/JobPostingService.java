package com.wanted.job.jobPosting.service;

import org.springframework.data.domain.Page;

import com.wanted.job.jobPosting.model.dto.JobPostingRequestDTO;
import com.wanted.job.jobPosting.model.dto.JobPostingResponseDTO;
import com.wanted.job.jobPosting.model.dto.JobPostingsPagingDTO;
import com.wanted.job.jobPosting.model.dto.PagedResponseDTO;
import com.wanted.job.jobPosting.model.entity.JobPosting;

public interface JobPostingService {
	void registerJobPosting(JobPostingRequestDTO request);

	void deleteJobPosting(Long jobPostingId);

	void updateJobPosting(Long jobPostingId, JobPostingRequestDTO request);
	JobPostingResponseDTO detailJobPosting(Long jobPostingId);

	PagedResponseDTO<JobPostingsPagingDTO> getAllJobPostings(int page, int size);
}
