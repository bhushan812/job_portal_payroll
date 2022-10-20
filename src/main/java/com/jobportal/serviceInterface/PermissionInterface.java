package com.jobportal.serviceInterface;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import com.jobportal.dto.IListJobDto;
import com.jobportal.dto.IPermissionListDto;
import com.jobportal.dto.JobDto;
import com.jobportal.dto.PermissionRequestDto;

public interface PermissionInterface {

	void addPermission(@Valid PermissionRequestDto permissionRequestDto);

	Page<IPermissionListDto> getAllPermissions(String search, String pageNo, String pageSize);

	PermissionRequestDto updatePermissions(PermissionRequestDto permissionRequestDto, Long id);

	
}
