package com.jobportal.serviceInterface;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jobportal.dto.IListJobDto;
import com.jobportal.dto.JobDto;
import com.jobportal.entity.JobEntity;

@Service
public interface JobInterface {

	

	Page<IListJobDto> getAllJobs(String search, String pagNumber, String pageSize);

	void addJobs(Long id, JobDto jobDto);

	JobDto updateJob(JobDto jobDto, Long id);

}
