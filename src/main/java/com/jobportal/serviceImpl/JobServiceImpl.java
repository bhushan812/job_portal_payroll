package com.jobportal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jobportal.dto.IListJobDto;
import com.jobportal.dto.JobDto;
import com.jobportal.entity.JobEntity;
import com.jobportal.excetpion.ResourceNotFoundException;
import com.jobportal.repositories.JobReposiotry;
import com.jobportal.serviceInterface.JobInterface;
import com.jobportal.utils.Pagination;

@Service
public class JobServiceImpl implements JobInterface {
	@Autowired
	private JobReposiotry jobReposiotry;

	@Override
	public void addJobs(Long id,JobDto jobDto) {
		System.err.println(1);
		JobEntity jobEntity = new JobEntity();
		jobEntity.setJobTitle(jobDto.getJobTitle());
		jobEntity.setDescription(jobDto.getDescription());
		jobEntity.setCreatedBy(id);
		this.jobReposiotry.save(jobEntity);

	}

	@Override
	public Page<IListJobDto> getAllJobs(String search, String pagNumber, String pageSize) {
		Page<IListJobDto> iJobListDto;

		System.err.println(11);
		Pageable pageable = new Pagination().getPagination(pagNumber, pageSize);
		System.err.println(12);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			System.err.println(13);
			iJobListDto = this.jobReposiotry.findByOrderByIdDesc(pageable, IListJobDto.class);
		}
		System.err.println(14);
		iJobListDto = this.jobReposiotry.findByJobTitleContainingIgnoreCase(search, pageable, IListJobDto.class);
		System.err.println(15);
		return iJobListDto;
	}

	@Override
	public JobDto updateJob(JobDto jobDto, Long id) {
		JobEntity jobEntity=	jobReposiotry.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Note Found"));
		jobEntity.setJobTitle(jobDto.getJobTitle());
		jobEntity.setDescription(jobDto.getDescription());
		jobEntity.setUpdatedBy(id);
		this.jobReposiotry.save(jobEntity);
		return jobDto;
	}


	

	
}
