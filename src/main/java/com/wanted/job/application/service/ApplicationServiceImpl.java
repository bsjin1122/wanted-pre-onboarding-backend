package com.wanted.job.application.service;


import org.springframework.stereotype.Service;

import com.wanted.job.application.exception.ApplicationErrorCode;
import com.wanted.job.application.exception.ApplicationException;
import com.wanted.job.application.model.dto.ApplicationRequestDTO;
import com.wanted.job.application.model.entity.Application;
import com.wanted.job.application.repository.ApplicationRepository;
import com.wanted.job.common.type.ApplicationStatus;
import com.wanted.job.jobPosting.exception.JobPostingErrorCode;
import com.wanted.job.jobPosting.exception.JobPostingException;
import com.wanted.job.jobPosting.model.entity.JobPosting;
import com.wanted.job.jobPosting.repository.JobPostingRepository;
import com.wanted.job.user.model.entity.User;
import com.wanted.job.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{
	private final ApplicationRepository jobApplicationRepository;
	private final UserRepository userRepository;
	private final JobPostingRepository jobPostingRepository;

	@Override
	@Transactional
	public void jobApplicate(ApplicationRequestDTO request) {
		User user = userRepository.findById(request.getUserId())
			.orElseThrow(() -> new ApplicationException(ApplicationErrorCode.USER_NOT_FOUND));

		JobPosting jobPosting = jobPostingRepository.findById(request.getJobPostingId())
			.orElseThrow(() -> new JobPostingException(JobPostingErrorCode.JOB_POSTING_NOT_FOUND));

		boolean alreadyApplied = jobApplicationRepository.existsByUser_UserIdAndJobPostingId(request.getUserId(), request.getJobPostingId());
		if (alreadyApplied) {
			throw new ApplicationException(ApplicationErrorCode.ALREADY_APPLIED);
		}

		Application jobApplication = Application.builder()
			.user(user)
			.jobPosting(jobPosting)
			.status(ApplicationStatus.PENDING)
			.resume(request.getResume())
			.build();

		jobApplicationRepository.save(jobApplication);
	}
}
