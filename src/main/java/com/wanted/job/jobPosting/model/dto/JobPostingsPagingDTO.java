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
public class JobPostingsPagingDTO {
	private Long jobPostId;
	private Long companyId;
	private String companyName;
	private String country;
	private String region;

	private String positionTitle;
	private int hiringBonus;
	private String skillsRequired;
}
