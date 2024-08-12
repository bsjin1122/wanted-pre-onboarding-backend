package com.wanted.job.jobPosting.model.dto;

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
public class JobPostingRequestDTO {
	private Long companyId;

	private String positionTitle;
	private int hiringBonus;
	private String jobDescription;
	private String skillsRequired;
}
