package com.jobportal.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.dto.IPermissionListDto;
import com.jobportal.entity.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

	Page<IPermissionListDto> findByOrderByIdDesc(Pageable paging, Class<IPermissionListDto> class1);

	Page<IPermissionListDto> findByActionNameContaining(String search, Pageable paging,
			Class<IPermissionListDto> class1);

			
	
	
}
