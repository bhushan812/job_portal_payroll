package com.jobportal.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.dto.IRoleListDto;
import com.jobportal.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	RoleEntity findByRoleNameContainingIgnoreCase(String roleName);

	Page<IRoleListDto> findByOrderByIdAsc(Pageable pageable, Class<IRoleListDto> class1);

	Page<IRoleListDto> findByRoleNameIgnoreCaseContaining(String roleName, Pageable pageable,
			Class<IRoleListDto> class1);

}
