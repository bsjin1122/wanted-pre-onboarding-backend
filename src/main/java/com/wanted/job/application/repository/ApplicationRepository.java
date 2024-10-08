package com.wanted.job.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.job.application.model.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	boolean existsByUser_UserIdAndJobPostingId(Long userId, Long jobPostingId);
	void deleteByJobPostingId(Long jobPostingId);
}
