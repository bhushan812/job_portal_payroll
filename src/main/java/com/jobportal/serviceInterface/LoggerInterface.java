package com.jobportal.serviceInterface;

import org.springframework.stereotype.Service;

import com.jobportal.dto.LoggerDto;
import com.jobportal.entity.LoggerEntity;
import com.jobportal.entity.UserEntity;

@Service
public interface LoggerInterface {

	LoggerEntity createLogger(LoggerDto loggerDto, UserEntity userEntity);

	LoggerEntity getLoggerDetail(String token);
}
