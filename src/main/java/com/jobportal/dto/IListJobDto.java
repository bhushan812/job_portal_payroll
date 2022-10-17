package com.jobportal.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface IListJobDto {
	public String getJobTitle();

	public String getDescription();

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getJobPostDate();

	public Long getId();

}
