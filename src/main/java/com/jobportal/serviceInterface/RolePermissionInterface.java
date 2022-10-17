package com.jobportal.serviceInterface;

import org.springframework.stereotype.Service;

import com.jobportal.dto.RolePermissionDto;

@Service
public interface RolePermissionInterface {
	public RolePermissionDto addRolePermission(RolePermissionDto rolePermissionDto);

}
