package com.jobportal.serviceInterface;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jobportal.dto.IListJobDto;
import com.jobportal.dto.JobDto;

@Service
public interface JobInterface {

	void addJobs(JobDto jobDto);

	Page<IListJobDto> getAllJobs(String search, String pagNumber, String pageSize);

}
