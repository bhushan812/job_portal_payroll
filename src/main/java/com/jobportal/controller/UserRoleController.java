package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ErrorResponseDto;
import com.jobportal.dto.SuccessResponseDto;
import com.jobportal.dto.UserRoleDto;
import com.jobportal.serviceInterface.UserRoleInterface;

@RestController
@RequestMapping("/user-role")
public class UserRoleController {

	@Autowired
	private UserRoleInterface userRoleInterface;

	@PostMapping()
	public ResponseEntity<?> addUserRole(@RequestBody UserRoleDto userRoleDto) {
		try {

			UserRoleDto dto = this.userRoleInterface.addUserRole(userRoleDto);

			return new ResponseEntity<>(new SuccessResponseDto("Assign Succefully", "Assigned", dto), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "Please Enter Valid User OR Role"),
					HttpStatus.BAD_REQUEST);
		}

	}

}
