package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ErrorResponseDto;
import com.jobportal.dto.IRoleListDto;
import com.jobportal.dto.RoleDto;
import com.jobportal.dto.SuccessResponseDto;
import com.jobportal.entity.RoleEntity;
import com.jobportal.excetpion.ResourceNotFoundException;
import com.jobportal.repositories.RoleRepository;
import com.jobportal.serviceInterface.RoleInterface;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleInterface roleInterface;
	
	@PreAuthorize("hasRole('addRoles')")
	@PostMapping()
	public ResponseEntity<?> addRoles(@RequestBody RoleDto roleDto) {

		try {
			RoleEntity roleEntity = this.roleRepository.findByRoleNameContainingIgnoreCase(roleDto.getRoleName());
			if (roleEntity == null) {
				RoleDto roleDto1 = this.roleInterface.addRole(roleDto);
				return new ResponseEntity<>(new SuccessResponseDto("Role Added Successfully", "Role Added", roleDto1),
						HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(new ErrorResponseDto("Role already exist", "Please add new role"),
						HttpStatus.BAD_REQUEST);
			}

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok(new ErrorResponseDto("Enter valid role.", "Invalid role"));
		}
	}
	@PreAuthorize("hasRole('getRoleById')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getRoleById(@PathVariable Long id) {
		try {
			RoleDto roleDto = roleInterface.getRoleById(id);
			return new ResponseEntity<>(new SuccessResponseDto("Role Get Successfully", "Success", roleDto),
					HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "Role not found "),
					HttpStatus.BAD_REQUEST);

		}

	}
	@PreAuthorize("hasRole('getAllRoles')")
	@GetMapping()
	public ResponseEntity<?> getAllRoles(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "5") String PageSize) {
		Page<IRoleListDto> roles = roleInterface.getAllRoles(search, pageNo, PageSize);

		if (roles.getTotalElements() != 0) {

			return new ResponseEntity<>(
					new SuccessResponseDto("All Roles get Successfully", "Success", roles.getContent()), HttpStatus.OK);

		}

		return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "Data Not Found"), HttpStatus.NOT_FOUND);
	}
	@PreAuthorize("hasRole('updateRole')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRole(@RequestBody RoleDto roleDto, @PathVariable long id) {
		try {
			roleDto = this.roleInterface.updateRoles(roleDto, id);
			return new ResponseEntity<>(new SuccessResponseDto("Role updated sucessfully", "Role updated !!", roleDto),
					HttpStatus.CREATED);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(new ErrorResponseDto("Role Id Not Found  ", "Something went wrong"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
		try {
			roleInterface.deleteRole(id);

			return ResponseEntity.ok(new SuccessResponseDto("Deleted Succesfully", "Deleted", id));
		} catch (Exception e) {

			return ResponseEntity.ok(new ErrorResponseDto(e.getMessage(), "Enter Valid Id"));
		}
	}

}
