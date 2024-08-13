package com.wanted.job.jobPosting.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wanted.job.company.exception.CompanyErrorCode;
import com.wanted.job.company.exception.CompanyException;
import com.wanted.job.company.model.entity.Company;
import com.wanted.job.company.repository.CompanyRepository;
import com.wanted.job.jobPosting.exception.JobPostingErrorCode;
import com.wanted.job.jobPosting.exception.JobPostingException;
import com.wanted.job.jobPosting.model.dto.JobPostingRequestDTO;
import com.wanted.job.jobPosting.model.dto.JobPostingResponseDTO;
import com.wanted.job.jobPosting.model.dto.JobPostingsPagingDTO;
import com.wanted.job.jobPosting.model.dto.PagedResponseDTO;
import com.wanted.job.jobPosting.model.entity.JobPosting;
import com.wanted.job.jobPosting.repository.JobPostingRepository;

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
			Company company = Company.builder()
				.country(request.getCountry())
				.name(request.getCompanyName())
				.region(request.getRegion()).build();

			companyRepository.save(company);

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

	@Override
	public void deleteJobPosting(Long jobPostingId) {
		JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
			.orElseThrow(()-> new JobPostingException(JobPostingErrorCode.JOB_POSTING_NOT_FOUND));
		jobPostingRepository.delete(jobPosting);
	}

	@Override
	public void updateJobPosting(Long jobPostingId, JobPostingRequestDTO request) {
		Optional<JobPosting> jobPosting = jobPostingRepository.findById(jobPostingId);
		if(jobPosting.isEmpty()){
			throw new JobPostingException(JobPostingErrorCode.JOB_POSTING_NOT_FOUND);
		}

		JobPosting existJobPosting = jobPosting.get();
		existJobPosting.updateJobPost(
			request.getPositionTitle(),
			request.getHiringBonus(),
			request.getJobDescription(),
			request.getSkillsRequired()
		);
		jobPostingRepository.save(existJobPosting);
	}

	@Override
	public JobPostingResponseDTO detailJobPosting(Long jobPostingId) {
		JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
			.orElseThrow(() -> new JobPostingException(JobPostingErrorCode.JOB_POSTING_NOT_FOUND));

		JobPostingResponseDTO responseDTO = JobPostingResponseDTO.builder()
			.jobPostId(jobPostingId)
			.companyId(jobPosting.getCompany().getId())
			.country(jobPosting.getCompany().getCountry())
			.region(jobPosting.getCompany().getRegion())
			.positionTitle(jobPosting.getPositionTitle())
			.jobDescription(jobPosting.getJobDescription())
			.hiringBonus(jobPosting.getHiringBonus())
			.skillsRequired(jobPosting.getSkillsRequired())
			.jobPostings(jobPosting.getCompany().getJobPostings().stream()
				.map(JobPosting::getId)  // JobPosting 객체에서 ID만 추출
				.collect(Collectors.toList())) // ID 리스트로 변환
			.build();

		return responseDTO;
	}

	@Override
	public PagedResponseDTO<JobPostingsPagingDTO> getAllJobPostings(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<JobPosting> jobPostings = jobPostingRepository.findAll(pageable);

		Page<JobPostingsPagingDTO> response = jobPostings.map(jobPosting ->
			JobPostingsPagingDTO.builder()
				.jobPostId(jobPosting.getId())
				.companyName(jobPosting.getCompany().getName())
				.country(jobPosting.getCompany().getCountry())
				.region(jobPosting.getCompany().getRegion())
				.positionTitle(jobPosting.getPositionTitle())
				.hiringBonus(jobPosting.getHiringBonus())
				.skillsRequired(jobPosting.getSkillsRequired())
				.build());
		return new PagedResponseDTO<>(response);
	}

	@Override
	public PagedResponseDTO<JobPostingsPagingDTO> searchJobPostings(String keyword, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<JobPosting> jobPostings = jobPostingRepository.findByKeywordInAllFields(keyword, pageable);

		Page<JobPostingsPagingDTO> response = jobPostings.map(jobPosting ->
			JobPostingsPagingDTO.builder()
				.jobPostId(jobPosting.getId())
				.companyId(jobPosting.getCompany().getId())
				.companyName(jobPosting.getCompany().getName())
				.country(jobPosting.getCompany().getCountry())
				.region(jobPosting.getCompany().getRegion())
				.positionTitle(jobPosting.getPositionTitle())
				.hiringBonus(jobPosting.getHiringBonus())
				.skillsRequired(jobPosting.getSkillsRequired())
				.build());
		return new PagedResponseDTO<>(response);
	}
}
