package com.jobportal.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.dto.IListJobDto;
import com.jobportal.entity.JobEntity;

@Repository
public interface JobReposiotry extends JpaRepository<JobEntity, Long> {

	Page<IListJobDto> findByOrderByIdDesc(Pageable pageable, Class<IListJobDto> class1);

	Page<IListJobDto> findByJobTitleContainingIgnoreCase(String search, Pageable pageable, Class<IListJobDto> class1);

	// Page<IJobListDto> findByOrderByIdAsc(Pageable pageable, Class<IJobListDto>
	// class1);

}
