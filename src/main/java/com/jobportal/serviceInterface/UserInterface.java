package com.jobportal.serviceInterface;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jobportal.dto.IListUserDto;
import com.jobportal.dto.UserPersonalInfoDto;

@Service
public interface UserInterface {

	void upateUser(UserPersonalInfoDto personalInfoDto, Long id);

	Page<IListUserDto> getAllUsers(String search, String pageNo, String PageSize);

}
