package com.wanted.job.jobPosting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wanted.job.jobPosting.model.entity.JobPosting;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
	Page<JobPosting> findAll(Pageable pageable);
	@Query("SELECT jp FROM JobPosting jp WHERE " +
		"jp.positionTitle LIKE %:keyword% OR " +
		"CAST(jp.hiringBonus AS string) LIKE %:keyword% OR " +
		"jp.skillsRequired LIKE %:keyword% OR " +
		"jp.company.name LIKE %:keyword%")
	Page<JobPosting> findByKeywordInAllFields(@Param("keyword") String keyword, Pageable pageable);


}
