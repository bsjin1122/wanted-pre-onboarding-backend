package com.wanted.job.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
@Controller
@AllArgsConstructor
public class ApplicationController {
	private final ApplicationService applicationService;
	@GetMapping("/apply")
	public String showApplyForm() {
		return "apply"; // 지원하기 화면을 나타내는 Thymeleaf 템플릿 파일 이름
	}
	@PostMapping("/apply")
	public ResponseEntity<String> applyJobApplication(@RequestBody ApplicationRequestDTO request){
		applicationService.jobApplicate(request);
		return ResponseEntity.ok("지원이 완료되었습니다.");
	}
}
