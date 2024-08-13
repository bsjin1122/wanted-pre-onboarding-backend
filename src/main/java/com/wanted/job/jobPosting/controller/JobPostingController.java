package com.wanted.job.jobPosting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.job.jobPosting.model.dto.JobPostingRequestDTO;
import com.wanted.job.jobPosting.model.dto.JobPostingResponseDTO;
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

}
