package com.jobportal.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.dto.IUserJobListDto;
import com.jobportal.entity.UserJob;

public interface UserJobRepository extends JpaRepository<UserJob, Long> {

	UserJob findByUserIdAndJobId(Long userId, Long long1);


	Page<IUserJobListDto> findByUserIdOrderByJobIdDesc(Pageable pageable, Long id, Class<IUserJobListDto> class1);


	Page<IUserJobListDto> findByJobIdOrderByUserIdDesc(Long jobId, Pageable pageable, Class<IUserJobListDto> class1);


	Page<IUserJobListDto> findByOrderByIdDesc(Pageable pageable, Class<IUserJobListDto> class1);

	
	
//	UserJob findByUserIdAndJobId(Long userId, Long long1);

	// void saveAll(ArrayList<UserJob> jobs);

}
