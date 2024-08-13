package com.wanted.job.company.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.wanted.job.common.entity.BaseTimeEntity;
import com.wanted.job.jobPosting.model.entity.JobPosting;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "company")
@Entity
@Builder
public class Company extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(name = "company_name", nullable = false, length = 100)
	private String name;

	@Column(nullable = false, length = 100)
	private String country;

	@Column(nullable = false, length = 100)
	private String region;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<JobPosting> jobPostings = new ArrayList<>();

	public void addJobPosting(JobPosting jobPosting) {
		jobPostings.add(jobPosting);
		jobPosting.setCompany(this);
	}

	public void removeJobPosting(JobPosting jobPosting) {
		jobPostings.remove(jobPosting);
		jobPosting.setCompany(null);
	}
}
