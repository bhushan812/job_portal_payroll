package com.jobportal.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PermissionRequestDto {
	@NotBlank(message = "actionName is Required*actionNameRequired")
	@NotEmpty(message = "actionName is Required*actionNameRequired")
	@NotNull(message = "actionName is Required*actionNameRequired")
	private String actionName;

	private String baseUrl;

	private String description;

	private String method;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public PermissionRequestDto(String actionName, String baseUrl, String description, String method) {
		super();
		this.actionName = actionName;
		this.baseUrl = baseUrl;
		this.description = description;
		this.method = method;

	}

	public PermissionRequestDto() {
		super();
	}

}
