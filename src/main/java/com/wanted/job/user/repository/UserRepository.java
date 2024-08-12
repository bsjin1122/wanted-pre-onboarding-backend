package com.wanted.job.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.job.user.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
