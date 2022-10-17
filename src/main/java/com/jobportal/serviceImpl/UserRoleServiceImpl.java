package com.jobportal.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.UserRoleDto;
import com.jobportal.entity.RoleEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.entity.UserRoleEntity;
import com.jobportal.excetpion.ResourceNotFoundException;
import com.jobportal.repositories.AuthRepository;
import com.jobportal.repositories.RoleRepository;
import com.jobportal.repositories.UserRoleRepository;
import com.jobportal.serviceInterface.UserRoleInterface;

@Service
public class UserRoleServiceImpl implements UserRoleInterface {
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private AuthRepository authRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserRoleDto addUserRole(UserRoleDto userRoleDto) {
		UserRoleEntity userRoleEntity = this.userRoleRepository.findByUserIdAndRoleId(userRoleDto.getUserId(),
				userRoleDto.getRoleId());

		if (userRoleEntity == null) {

			UserEntity userEntity = this.authRepository.findById(userRoleDto.getUserId())
					.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

			RoleEntity roleEntity = this.roleRepository.findById(userRoleDto.getRoleId())
					.orElseThrow(() -> new ResourceNotFoundException("Role Does Not Found"));

			ArrayList<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();

			UserRoleEntity newUserRoleEntity = new UserRoleEntity();
			newUserRoleEntity.setUser(userEntity);
			newUserRoleEntity.setRole(roleEntity);
			userRoleEntityList.add(newUserRoleEntity);
			userRoleRepository.saveAll(userRoleEntityList);

		} else {
			throw new ResourceNotFoundException("resource already exists");

		}
		return userRoleDto;

	}

}
