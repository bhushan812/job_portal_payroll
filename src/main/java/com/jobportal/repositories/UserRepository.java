package com.jobportal.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.dto.IListUserDto;
import com.jobportal.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Page<IListUserDto> findByOrderByIdDesc(Pageable pageable, Class<IListUserDto> class1);

	Page<IListUserDto> findByNameContainingIgnoreCase(String search, Pageable pageable, Class<IListUserDto> class1);

}
