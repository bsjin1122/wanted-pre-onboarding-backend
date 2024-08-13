package com.wanted.job.jobPosting;

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
import com.wanted.job.jobPosting.service.JobPostingServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JobPostingServiceImplTest {

	@Mock
	private CompanyRepository companyRepository;

	@Mock
	private JobPostingRepository jobPostingRepository;

	@InjectMocks
	private JobPostingServiceImpl jobPostingService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void registerJobPosting_success() {
		JobPostingRequestDTO request = JobPostingRequestDTO.builder()
			.companyId(1L)
			.positionTitle("웹개발자")
			.companyName("회사1")
			.country("미국")
			.region("캘리포니아")
			.hiringBonus(50000)
			.skillsRequired("Java, Spring")
			.jobDescription("같이 일할 팀원을 구해요!")
			.build();

		Company company = Company.builder()
			.id(1L)
			.name("회사1")
			.country("미국")
			.region("캘리포니아")
			.build();

		when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

		jobPostingService.registerJobPosting(request);

		verify(companyRepository).findById(1L);
		verify(jobPostingRepository).save(any(JobPosting.class));
	}

	@Test
	void registerJobPosting_companyNotFound() {
		JobPostingRequestDTO request = JobPostingRequestDTO.builder()
			.companyId(1L)
			.build();

		when(companyRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(CompanyException.class, () -> jobPostingService.registerJobPosting(request));
	}

	@Test
	void deleteJobPosting_success() {
		JobPosting jobPosting = JobPosting.builder()
			.id(1L)
			.build();

		when(jobPostingRepository.findById(1L)).thenReturn(Optional.of(jobPosting));

		jobPostingService.deleteJobPosting(1L);

		verify(jobPostingRepository).findById(1L);
		verify(jobPostingRepository).delete(jobPosting);
	}

	@Test
	void deleteJobPosting_notFound() {
		when(jobPostingRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(JobPostingException.class, () -> jobPostingService.deleteJobPosting(1L));
	}

	@Test
	void updateJobPosting_success() {
		JobPostingRequestDTO request = JobPostingRequestDTO.builder()
			.positionTitle("시니어 개발자")
			.build();

		JobPosting existingJobPosting = JobPosting.builder()
			.id(1L)
			.build();

		when(jobPostingRepository.findById(1L)).thenReturn(Optional.of(existingJobPosting));

		jobPostingService.updateJobPosting(1L, request);

		verify(jobPostingRepository).findById(1L);
		verify(jobPostingRepository).save(existingJobPosting);
	}

	@Test
	void updateJobPosting_notFound() {
		JobPostingRequestDTO request = JobPostingRequestDTO.builder()
			.positionTitle("시니어 개발자")
			.build();

		when(jobPostingRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(JobPostingException.class, () -> jobPostingService.updateJobPosting(1L, request));
	}

	@Test
	void detailJobPosting_success() {
		JobPosting jobPosting = JobPosting.builder()
			.id(1L)
			.positionTitle("웹개발자")
			.hiringBonus(50000)
			.jobDescription("유지보수 일을 합니다.")
			.skillsRequired("Java")
			.company(Company.builder()
				.id(1L)
				.name("회사1")
				.country("미국")
				.region("캘리포니아")
				.build())
			.build();

		when(jobPostingRepository.findById(1L)).thenReturn(Optional.of(jobPosting));

		JobPostingResponseDTO responseDTO = jobPostingService.detailJobPosting(1L);

		assertNotNull(responseDTO);
		assertEquals(1L, responseDTO.getJobPostId());
	}

	@Test
	void detailJobPosting_notFound() {
		when(jobPostingRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(JobPostingException.class, () -> jobPostingService.detailJobPosting(1L));
	}

	@Test
	void getAllJobPostings_success() {
		JobPosting jobPosting = JobPosting.builder()
			.id(1L)
			.positionTitle("SI 개발자")
			.hiringBonus(5000)
			.skillsRequired("Java, JSP")
			.company(Company.builder()
				.name("회사2")
				.country("일본")
				.region("도쿄")
				.build())
			.build();

		Pageable pageable = PageRequest.of(0, 10);
		Page<JobPosting> page = new PageImpl<>(Collections.singletonList(jobPosting), pageable, 1);

		when(jobPostingRepository.findAll(pageable)).thenReturn(page);

		PagedResponseDTO<JobPostingsPagingDTO> response = jobPostingService.getAllJobPostings(0, 10);

		assertNotNull(response);
		assertEquals(1, response.getContent().size());
	}

	@Test
	void searchJobPostings_success() {
		JobPosting jobPosting = JobPosting.builder()
			.id(1L)
			.positionTitle("웹개발자")
			.hiringBonus(50000)
			.skillsRequired("Java, Spring")
			.company(Company.builder()
				.id(1L)
				.name("회사1")
				.country("미국")
				.region("캘리포니아")
				.build())
			.build();

		Pageable pageable = PageRequest.of(0, 10);
		Page<JobPosting> page = new PageImpl<>(Collections.singletonList(jobPosting), pageable, 1);

		when(jobPostingRepository.findByKeywordInAllFields("웹", pageable)).thenReturn(page);

		PagedResponseDTO<JobPostingsPagingDTO> response = jobPostingService.searchJobPostings("웹", 0, 10);

		assertNotNull(response);
		assertEquals(1, response.getContent().size());
	}
}