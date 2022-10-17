package com.jobportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entity.UserEntity;

public interface AuthRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmailContainingIgnoreCase(String email);

}
