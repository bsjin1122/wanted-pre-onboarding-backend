package com.wanted.job.jobPosting.model.entity;

import com.wanted.job.common.entity.BaseTimeEntity;
import com.wanted.job.company.model.entity.Company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "job_posting")
@Entity
@Builder
public class JobPosting extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false, length = 255)
	private String positionTitle;

	@Column
	private Integer hiringBonus;

	@Column(nullable = false, length = 255)
	private String jobDescription;

	@Column(nullable = false, length = 255)
	private String skillsRequired;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

}
