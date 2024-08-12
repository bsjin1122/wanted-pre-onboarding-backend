package com.wanted.job.jobPosting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.job.jobPosting.model.entity.JobPosting;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
}
