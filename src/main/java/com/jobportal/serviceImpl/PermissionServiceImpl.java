package com.jobportal.serviceImpl;






import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.jobportal.dto.IPermissionListDto;
import com.jobportal.dto.IUserJobListDto;
import com.jobportal.dto.PermissionRequestDto;
import com.jobportal.entity.PermissionEntity;
import com.jobportal.repositories.PermissionRepository;
import com.jobportal.serviceInterface.PermissionInterface;
import com.jobportal.utils.Pagination;
@Service
public class PermissionServiceImpl implements PermissionInterface {

	@Autowired
	PermissionRepository permissionRepository;

	@Override
	public void addPermission(@Valid PermissionRequestDto permissionRequestDto) {
		PermissionEntity permissionEntity = new PermissionEntity();
		permissionEntity.setActionName(permissionRequestDto.getActionName());
		permissionEntity.setBaseUrl(permissionRequestDto.getBaseUrl());
		permissionEntity.setDescription(permissionRequestDto.getDescription());
		permissionEntity.setMethod(permissionRequestDto.getMethod());
		permissionRepository.save(permissionEntity);
		
	}

	@Override
	public Page<IPermissionListDto> getAllPermissions(String search, String pageNo, String pageSize) {
		Pageable paging = new Pagination().getPagination(pageNo, pageSize);
		Page<IPermissionListDto> iListPermissionDto;

		if ((search == "") || (search == null) || (search.length() == 0)) {
			iListPermissionDto = this.permissionRepository.findByOrderByIdAsc(paging, IPermissionListDto.class);
		} else {
			iListPermissionDto = this.permissionRepository.findByActionNameContaining(search, paging, IPermissionListDto.class);
		}
		return iListPermissionDto;
	}

	
}
