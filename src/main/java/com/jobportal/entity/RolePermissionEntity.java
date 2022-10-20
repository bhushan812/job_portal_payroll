package com.jobportal.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "role_permission")
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE role_permission SET is_active=false WHERE id=?")
public class RolePermissionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch =FetchType.LAZY)
	public RoleEntity roleEntity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	public PermissionEntity permissionEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}

	public PermissionEntity getPermissionEntity() {
		return permissionEntity;
	}

	public void setPermissionEntity(PermissionEntity permissionEntity) {
		this.permissionEntity = permissionEntity;
	}

	public RolePermissionEntity(Long id, RoleEntity roleEntity, PermissionEntity permissionEntity) {
		super();
		this.id = id;
		this.roleEntity = roleEntity;
		this.permissionEntity = permissionEntity;
	}

	public RolePermissionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

	
	
}
