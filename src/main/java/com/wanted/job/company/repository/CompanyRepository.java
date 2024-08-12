package com.wanted.job.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.job.company.model.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
