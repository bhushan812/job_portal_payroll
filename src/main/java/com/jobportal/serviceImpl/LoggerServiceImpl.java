package com.jobportal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.LoggerDto;
import com.jobportal.entity.LoggerEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.repositories.LoggerRepository;
import com.jobportal.serviceInterface.LoggerInterface;

@Service("LoggerServiceImpl")
public class LoggerServiceImpl implements LoggerInterface {

	@Autowired
	private LoggerRepository loggerRepository;

	@Override
	public LoggerEntity createLogger(LoggerDto loggerDto, UserEntity userEntity) {
		LoggerEntity logger = new LoggerEntity();

		logger.setUserId(userEntity);
		logger.setToken(loggerDto.getToken());
		logger.setExpireAt(loggerDto.getExpireAt());
		loggerRepository.save(logger);
		return logger;
	}

	@Override
	public LoggerEntity getLoggerDetail(String token) {

		LoggerEntity loggerEntity;

		loggerEntity = this.loggerRepository.findByToken(token);
		return loggerEntity;
	}

}
