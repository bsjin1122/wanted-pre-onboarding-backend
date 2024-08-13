package com.wanted.job.jobPosting.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.job.jobPosting.exception.JobPostingErrorCode;
import com.wanted.job.jobPosting.exception.JobPostingException;
import com.wanted.job.jobPosting.model.dto.JobPostingRequestDTO;
import com.wanted.job.jobPosting.model.dto.JobPostingResponseDTO;
import com.wanted.job.jobPosting.model.dto.JobPostingsPagingDTO;
import com.wanted.job.jobPosting.model.dto.PagedResponseDTO;
import com.wanted.job.jobPosting.service.JobPostingService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/job-posting")
@RestController
@AllArgsConstructor
public class JobPostingController {

	private final JobPostingService jobPostingService;
	@PostMapping("/register")
	public ResponseEntity<String> registerJobPosting(@RequestBody JobPostingRequestDTO request){
		jobPostingService.registerJobPosting(request);
		return ResponseEntity.ok("채용 공고 등록이 완료되었습니다.");
	}

	@DeleteMapping("/{jobPostingId}")
	public ResponseEntity<String> deleteJobPosting(@PathVariable(value="jobPostingId") Long jobPostingId){
		jobPostingService.deleteJobPosting(jobPostingId);
		return ResponseEntity.ok("채용 공고 삭제가 완료되었습니다.");
	}

	@PutMapping("/update/{jobPostingId}")
	public ResponseEntity<String> updateJobPosting(@PathVariable(value="jobPostingId") Long jobPostingId, @RequestBody JobPostingRequestDTO request){
		jobPostingService.updateJobPosting(jobPostingId, request);
		return ResponseEntity.ok("채용 공고 수정이 완료되었습니다.");
	}

	@GetMapping("/detail/{jobPostingId}")
	public ResponseEntity<JobPostingResponseDTO> getJobPostingDetailById(@PathVariable(value="jobPostingId") Long jobPostingId){
		return ResponseEntity.ok(jobPostingService.detailJobPosting(jobPostingId));
	}

	@GetMapping("/")
	public ResponseEntity<PagedResponseDTO<JobPostingsPagingDTO>> getAllJobPostings(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	){
		PagedResponseDTO<JobPostingsPagingDTO> response = jobPostingService.getAllJobPostings(page, size);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/search")
	public ResponseEntity<PagedResponseDTO<JobPostingsPagingDTO>> searchJobPostings(
		@RequestParam String keyword,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size
	){
		// 검색어 길이 유효성 검사 (예: 최대 50자)
		if (keyword.length() > 50) {
			throw new JobPostingException(JobPostingErrorCode.JOB_POSTING_EXCEED_KEYWORD);
		}

		PagedResponseDTO<JobPostingsPagingDTO> response = jobPostingService.searchJobPostings(keyword, page, size);
		return ResponseEntity.ok(response);
	}

}
