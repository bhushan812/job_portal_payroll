package com.jobportal.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

	@Override
	public void applyMultipleJob(UserJobDto userJobDto) throws Exception {

		ArrayList<UserJob> jobs = new ArrayList<>();
		UserEntity userEntity = this.userRepository.findById(userJobDto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Enter valid user id"));

		for (int i = 0; i < userJobDto.getJobId().size(); i++) {

			final long jobId = userJobDto.getJobId().get(i);

			JobEntity job = this.jobReposiotry.findById(jobId)
					.orElseThrow(() -> new ResourceNotFoundException("Enter job id not found"));

			UserJob userJob = this.userJobRepository.findByUserIdAndJobId(userJobDto.getUserId(),
					userJobDto.getJobId().get(i));

			if (userJob != null) {

				throw new ResourceNotFoundException("Already applied ");
			}

			UserJob userJobs = new UserJob();
			userJobs.setUser(userEntity);
			userJobs.setJob(job);
			jobs.add(userJobs);

		}
		this.userJobRepository.saveAll(jobs);

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
