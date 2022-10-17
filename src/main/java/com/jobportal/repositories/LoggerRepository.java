package com.jobportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.entity.LoggerEntity;

@Repository
public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {

	LoggerEntity findByToken(String token);

}
