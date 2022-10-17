package com.jobportal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobportal.dto.IListUserDto;
import com.jobportal.dto.UserPersonalInfoDto;
import com.jobportal.entity.UserEntity;
import com.jobportal.repositories.UserRepository;
import com.jobportal.serviceInterface.UserInterface;
import com.jobportal.utils.Pagination;

@Service
public class UserServiceImpl implements UserInterface {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void upateUser(UserPersonalInfoDto userPersonalInfoDto, Long id) {
		UserEntity userEntity = this.userRepository.findById(id).orElseThrow();

		userEntity.setEducation(userPersonalInfoDto.getEducation());
		userEntity.setExperience(userPersonalInfoDto.getExperience());
		userEntity.setMobileNumber(userPersonalInfoDto.getMobileNumber());

		this.userRepository.save(userEntity);

	}

	@Override
	public Page<IListUserDto> getAllUsers(String search, String pageNo, String PageSize) {
		Page<IListUserDto> page;
		Pageable pageable = new Pagination().getPagination(pageNo, PageSize);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			page = this.userRepository.findByOrderByIdDesc(pageable, IListUserDto.class);
		}
		page = this.userRepository.findByNameContainingIgnoreCase(search, pageable, IListUserDto.class);

		return page;
	}

}
