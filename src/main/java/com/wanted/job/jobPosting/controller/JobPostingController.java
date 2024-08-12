package com.wanted.job.jobPosting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.job.jobPosting.model.dto.JobPostingRequestDTO;
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

}
