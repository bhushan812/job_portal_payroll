package com.jobportal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ErrorResponseDto;

import com.jobportal.dto.IPermissionListDto;
import com.jobportal.dto.PermissionRequestDto;
import com.jobportal.dto.SuccessResponseDto;
import com.jobportal.excetpion.ResourceNotFoundException;
import com.jobportal.serviceInterface.PermissionInterface;

@RestController
@RequestMapping("permission")
public class PermissionController {
	@Autowired
	private PermissionInterface permissionInterface;

	
	@PostMapping()
	public ResponseEntity<?> addPermission(@Valid @RequestBody PermissionRequestDto permissionRequestDto) {
		try {

			this.permissionInterface.addPermission(permissionRequestDto);

			return new ResponseEntity<>(
					new SuccessResponseDto("Permission added successfully", "Permission added", permissionRequestDto), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "Not added"), HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping()
	public ResponseEntity<?> getAllPermissions(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "50") String pageSize) {
		try {

			Page<IPermissionListDto> list = this.permissionInterface.getAllPermissions(search, pageNo, pageSize);

			return new ResponseEntity<>(new SuccessResponseDto("Success", "Permissions", list), HttpStatus.ACCEPTED);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "Permissions not found"),
					HttpStatus.BAD_REQUEST);
		}
	}
}
