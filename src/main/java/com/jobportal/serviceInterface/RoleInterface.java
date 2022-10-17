package com.jobportal.serviceInterface;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jobportal.dto.IRoleListDto;
import com.jobportal.dto.RoleDto;

@Service
public interface RoleInterface {
	RoleDto addRole(RoleDto roleDto);

	RoleDto getRoleById(Long id);

	Page<IRoleListDto> getAllRoles(String roleName, String from, String to);

	RoleDto updateRoles(RoleDto roleDto, Long id);

	void deleteRole(Long id);
}
