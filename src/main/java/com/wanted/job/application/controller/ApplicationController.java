package com.wanted.job.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.job.application.model.dto.ApplicationRequestDTO;
import com.wanted.job.application.service.ApplicationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api/application")
@RestController
@AllArgsConstructor
public class ApplicationController {
	private final ApplicationService applicationService;

	@PostMapping("/apply")
	public ResponseEntity<String> applyJobApplication(@RequestBody ApplicationRequestDTO request){
		applicationService.jobApplicate(request);
		return ResponseEntity.ok("지원이 완료되었습니다.");
	}
}
