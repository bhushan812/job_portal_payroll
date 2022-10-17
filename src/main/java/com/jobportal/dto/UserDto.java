package com.jobportal.dto;

import javax.validation.constraints.Pattern;

public class UserDto {
	private String name;
	private String email;
	private String password;
	private String experience;
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String mobileNumber;
	private String education;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(String name, String email, String password, String experience, String mobileNumber,
			String education) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.experience = experience;
		this.mobileNumber = mobileNumber;
		this.education = education;
	}

}
