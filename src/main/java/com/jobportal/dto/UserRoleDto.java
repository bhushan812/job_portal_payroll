package com.jobportal.dto;

public class UserRoleDto {
	private Long id;
	private Long userId;
	private Long roleId;

	public UserRoleDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public UserRoleDto(Long id, Long userId, Long roleId) {
		super();
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
	}

}
