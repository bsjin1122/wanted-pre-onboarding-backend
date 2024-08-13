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
		return "apply";
	}
	@PostMapping("/apply")
	public String applyJobApplication(@RequestBody ApplicationRequestDTO request) {
		applicationService.jobApplicate(request);
		return "redirect:/api/job-posting/";
	}

}
