package com.jobportal.serviceInterface;

import org.springframework.stereotype.Service;

import com.jobportal.dto.UserRoleDto;

@Service
public interface UserRoleInterface {

	public UserRoleDto addUserRole(UserRoleDto userRoleDto);

}
