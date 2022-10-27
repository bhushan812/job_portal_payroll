package com.jobportal.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.dto.IListRolePermission;
import com.jobportal.entity.RolePermissionEntity;

public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity,Long>{

	Page<IListRolePermission> findByOrderByIdAsc(Pageable paging, Class<IListRolePermission> class1);

	RolePermissionEntity findByRolePermissionId(Long roleId, Long permissionId);

	List<IListRolePermission> findById(Long id, Class<IListRolePermission> class1);
	
	

}
