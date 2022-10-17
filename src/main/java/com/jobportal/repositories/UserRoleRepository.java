package com.jobportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entity.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

	UserRoleEntity findByUserIdAndRoleId(Long userId, Long roleId);

}
