package com.jobportal.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobportal.dto.EmailListDto;
import com.jobportal.dto.IListJobDto;
import com.jobportal.dto.IUserJobListDto;
import com.jobportal.dto.UserJobDto;
import com.jobportal.entity.JobEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.entity.UserJob;
import com.jobportal.excetpion.ResourceNotFoundException;
import com.jobportal.repositories.JobReposiotry;
import com.jobportal.repositories.UserJobRepository;
import com.jobportal.repositories.UserRepository;
import com.jobportal.repositories.UserRoleRepository;
import com.jobportal.serviceInterface.EmailServiceInterface;
import com.jobportal.serviceInterface.UserJobInterface;
import com.jobportal.utils.Pagination;

@Service
public class UserJobServiceImpl implements UserJobInterface {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	JobReposiotry jobReposiotry;

	@Autowired
	UserJobRepository userJobRepository;
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private EmailServiceInterface emailServiceInterface;

	@Override
	public void applyJobs(Long id, UserJobDto userJobDto) throws MessagingException {
		UserEntity userEntity = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Enter valid user id"));

		JobEntity job = this.jobReposiotry.findById(userJobDto.getJobId())
				.orElseThrow(() -> new ResourceNotFoundException("Enter job id not found"));

		UserJob userJob = this.userJobRepository.findByUserIdAndJobId(id, userJobDto.getJobId());

		if (userJob != null) {

			throw new ResourceNotFoundException("Already applied ");
		}

		UserJob userJobs = new UserJob();
		userJobs.setUser(userEntity);
		userJobs.setJob(job);

		this.userJobRepository.save(userJobs);

		UserEntity userEntity2 = this.userRepository.findById(id).orElseThrow();
		String email = userEntity2.getEmail();

		JobEntity jobEntity = this.jobReposiotry.findById(userJobDto.getJobId())
				.orElseThrow(() -> new ResourceNotFoundException("enter valid job id "));

		EmailListDto userEntity1 = this.userJobRepository.findAllUserEmail().get(0);

		emailServiceInterface.sendSimpleMessage(email, "Job jobs",
				"Job applied sucessfully for " + jobEntity.getJobTitle());

		emailServiceInterface.sendSimpleMessage(userEntity1.getEmail(), "Candidate jobs", "Candidate Applied for job"
				+ "Job title    " + jobEntity.getJobTitle() + "Candidate Email " + userEntity2.getEmail());
	}


	@Override
	public Page<IUserJobListDto> getByUserIdJobsList(Long UserId, String pageNo, String pageSize) {
		Page<IUserJobListDto> iUserJobListDto;

		System.err.println(11);
		Pageable pageable = new Pagination().getPagination(pageNo, pageSize);
		iUserJobListDto=	userJobRepository.findByUserIdOrderByJobIdDesc(pageable,UserId, IUserJobListDto.class);
		
		return iUserJobListDto;
	}

	@Override
	public Page<IUserJobListDto> getByJobIdUserList(Long jobId, String pageNo, String pageSize) {
		Page<IUserJobListDto> iUserJobListDto;

		Pageable pageable = new Pagination().getPagination(pageNo, pageSize);

		if ( jobId == null ) {

			iUserJobListDto = this.userJobRepository.findByOrderByIdDesc(pageable, IUserJobListDto.class);
		} else {

			iUserJobListDto = this.userJobRepository.findByJobIdOrderByUserIdDesc(jobId, pageable, IUserJobListDto.class);
		}
		return iUserJobListDto;
	}

}
