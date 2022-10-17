package com.jobportal.serviceInterface;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jobportal.dto.IListJobDto;
import com.jobportal.dto.IUserJobListDto;
import com.jobportal.dto.UserJobDto;

@Service
public interface UserJobInterface {
	void applyMultipleJob(UserJobDto userJobDto) throws Exception;

	Page<IUserJobListDto> getByUserIdJobsList(Long UserId, String pageNo, String pageSize);

	Page<IUserJobListDto> getByJobIdUserList(Long jobId, String pageNo, String pageSize);

	


	
}
