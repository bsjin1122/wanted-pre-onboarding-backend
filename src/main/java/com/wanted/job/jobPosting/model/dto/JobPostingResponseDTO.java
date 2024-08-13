package com.wanted.job.jobPosting.model.dto;

import java.util.List;

import com.wanted.job.jobPosting.model.entity.JobPosting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingResponseDTO {
	private Long jobPostId;
	private Long companyId;
	private String country;
	private String region;

	private String positionTitle;
	private int hiringBonus;
	private String jobDescription;
	private String skillsRequired;
	private List<Long> jobPostings;
}
