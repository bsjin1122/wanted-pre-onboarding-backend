package com.wanted.job.jobPosting.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wanted.job.company.exception.CompanyErrorCode;
import com.wanted.job.company.exception.CompanyException;
import com.wanted.job.company.model.entity.Company;
import com.wanted.job.company.repository.CompanyRepository;
import com.wanted.job.jobPosting.model.dto.JobPostingRequestDTO;
import com.wanted.job.jobPosting.model.entity.JobPosting;
import com.wanted.job.jobPosting.repository.JobPostingRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class JobPostingServiceImpl implements JobPostingService {

	private final CompanyRepository companyRepository;
	private final JobPostingRepository jobPostingRepository;


	@Override
	public void registerJobPosting(JobPostingRequestDTO request) {
		Optional<Company> companyOptional = companyRepository.findById(request.getCompanyId());

		if (companyOptional.isPresent()) {
			Company company = companyOptional.get();

			JobPosting jobPosting = JobPosting.builder()
				.positionTitle(request.getPositionTitle())
				.hiringBonus(request.getHiringBonus())
				.company(company)
				.skillsRequired(request.getSkillsRequired())
				.jobDescription(request.getJobDescription())
				.build();

			jobPostingRepository.save(jobPosting);
		} else {
			throw new CompanyException(CompanyErrorCode.COMPANY_ID_NOT_FOUND);
		}

	}
}
