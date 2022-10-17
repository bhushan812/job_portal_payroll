package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ErrorResponseDto;
import com.jobportal.dto.IListUserDto;
import com.jobportal.dto.SuccessResponseDto;
import com.jobportal.dto.UserPersonalInfoDto;
import com.jobportal.serviceInterface.UserInterface;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserInterface userInterface;

	@PatchMapping("/{id}")
	public ResponseEntity<?> upddateUserInformation(@RequestBody UserPersonalInfoDto personalInfoDto,
			@PathVariable Long id) {
		try {
			this.userInterface.upateUser(personalInfoDto, id);
			return new ResponseEntity<>(new SuccessResponseDto("update user information sucessfully", "success"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("enter valid information", "check valid data"),
					HttpStatus.OK);
		}
	}

	@GetMapping
	public ResponseEntity<?> getAlLUsers(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String PageNo, @RequestParam(defaultValue = "5") String PageSize) {

		Page<IListUserDto> iListUserDto = this.userInterface.getAllUsers(search, PageNo, PageSize);
		return new ResponseEntity<>(new SuccessResponseDto("All users", "success", iListUserDto.getContent()),
				HttpStatus.OK);

	}

}
