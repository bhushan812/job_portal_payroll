package com.jobportal.serviceInterface;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jobportal.dto.IListRolePermission;
import com.jobportal.dto.RolePermissionDto;

public interface RolePermissionInterface {

	public RolePermissionDto addRolePermission(RolePermissionDto rolePermissionDto);

	public RolePermissionDto updateRoleOrPermission(Long id, RolePermissionDto rolePermissionDto);

	public void deleteRolePermission(Long id);

	public List<IListRolePermission> getRolePermissionById(Long id);

	Page<IListRolePermission> getAllRolePermission(String search, String pageNumber, String pageSize);

	public ArrayList<String> getPermissionByUserId(Long id);
}
