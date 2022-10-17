package com.jobportal.dto;

import javax.validation.constraints.Pattern;

public class UserPersonalInfoDto {
	private String name;
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

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public UserPersonalInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserPersonalInfoDto(String name, String experience, @Pattern(regexp = "(^$|[0-9]{10})") String mobileNumber,
			String education) {
		super();
		this.name = name;
		this.experience = experience;
		this.mobileNumber = mobileNumber;
		this.education = education;
	}

}
