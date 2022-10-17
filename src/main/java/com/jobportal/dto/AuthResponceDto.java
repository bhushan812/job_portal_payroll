package com.jobportal.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class AuthResponceDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jwtToken;

	private ArrayList<String> permission;

	private String email;

	private String name;

	private Long id;

	public AuthResponceDto(String jwtToken, ArrayList<String> permission, String email, String name, Long id) {
		super();
		this.jwtToken = jwtToken;
		this.permission = permission;
		this.email = email;
		this.name = name;
		this.id = id;
	}

	public ArrayList<String> getPermission() {
		return permission;
	}

	public void setPermission(ArrayList<String> permission) {
		this.permission = permission;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AuthResponceDto(String jwtToken, String email, String name, Long id) {
		super();
		this.jwtToken = jwtToken;
		this.email = email;
		this.name = name;
		this.id = id;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJwtToken() {
		return jwtToken;
	}

}
