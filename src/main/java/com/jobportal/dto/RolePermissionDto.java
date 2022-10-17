package com.jobportal.dto;

import java.util.Objects;

public class RolePermissionDto {

	private Long roleId;
	private Long permissionId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public RolePermissionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(permissionId, roleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePermissionDto other = (RolePermissionDto) obj;
		return Objects.equals(permissionId, other.permissionId) && Objects.equals(roleId, other.roleId);
	}

}
