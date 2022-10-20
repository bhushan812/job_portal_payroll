package com.jobportal.repositories;

import java.util.List;

import javax.validation.constraints.Email;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jobportal.dto.EmailListDto;
import com.jobportal.dto.IUserJobListDto;
import com.jobportal.entity.UserJob;

public interface UserJobRepository extends JpaRepository<UserJob, Long> {

	UserJob findByUserIdAndJobId(Long userId, Long long1);


	Page<IUserJobListDto> findByUserIdOrderByJobIdDesc(Pageable pageable, Long id, Class<IUserJobListDto> class1);


	Page<IUserJobListDto> findByJobIdOrderByUserIdDesc(Long jobId, Pageable pageable, Class<IUserJobListDto> class1);


	Page<IUserJobListDto> findByOrderByIdDesc(Pageable pageable, Class<IUserJobListDto> class1);


	
	@Query(value = "select u.id as  user_id,u.email ,uu.created_by from users u inner join job uu on u.id=uu.created_by", nativeQuery = true)
	List<EmailListDto> findAllUserEmail();

	

	
	
//	UserJob findByUserIdAndJobId(Long userId, Long long1);

	// void saveAll(ArrayList<UserJob> jobs);

}
