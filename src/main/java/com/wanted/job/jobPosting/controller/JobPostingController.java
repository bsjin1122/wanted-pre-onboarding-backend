package com.wanted.job.jobPosting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@Controller
@AllArgsConstructor
public class JobPostingController {

	private final JobPostingService jobPostingService;
	@PostMapping("/register")
	public String registerJobPosting(@RequestBody JobPostingRequestDTO request){
		jobPostingService.registerJobPosting(request);
		return "redirect:/api/job-posting/";
	}

	@GetMapping("/register")
	public String showRegisterForm() {
		return "register";
	}

	@DeleteMapping("/{jobPostingId}")
	public ResponseEntity<String> deleteJobPosting(@PathVariable(value="jobPostingId") Long jobPostingId){
		try {
			jobPostingService.deleteJobPosting(jobPostingId);
			return ResponseEntity.ok("채용 공고 삭제가 완료되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("삭제 실패: " + e.getMessage());
		}
	}


	@PostMapping("/update/{jobPostingId}")
	public String updateJobPosting(@PathVariable(value="jobPostingId") Long jobPostingId,
		@RequestBody JobPostingRequestDTO request) {
		jobPostingService.updateJobPosting(jobPostingId, request);
		return "redirect:/api/job-posting/detail/" + jobPostingId; // 수정 후 상세 페이지로 리다이렉트
	}

	@GetMapping("/detail/{jobPostingId}")
	public String getJobPostingDetailById(Model model, @PathVariable(value="jobPostingId") Long jobPostingId){
		JobPostingResponseDTO jobPosting = jobPostingService.detailJobPosting(jobPostingId);
		model.addAttribute("jobPosting", jobPosting);
		return "detail";
	}

	@GetMapping("/")
	public String getAllJobPostings(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size,
		Model model
	){
		PagedResponseDTO<JobPostingsPagingDTO> response = jobPostingService.getAllJobPostings(page, size);

		model.addAttribute("jobPostings", response.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", response.getTotalPages());
		model.addAttribute("pageSize", size);
		return "index";
	}

	@GetMapping("/search")
	public String searchJobPostings(
		@RequestParam String keyword,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size,
		Model model
	){
		// 검색어 길이 유효성 검사 (예: 최대 50자)
		if (keyword.length() > 50) {
			throw new JobPostingException(JobPostingErrorCode.JOB_POSTING_EXCEED_KEYWORD);
		}

		PagedResponseDTO<JobPostingsPagingDTO> response = jobPostingService.searchJobPostings(keyword, page, size);

		// 모델에 데이터 추가
		model.addAttribute("jobPostings", response.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", response.getTotalPages());
		model.addAttribute("pageSize", size);
		model.addAttribute("keyword", keyword);

		// 반환할 뷰 이름
		return "index";
	}

}
