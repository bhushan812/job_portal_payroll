package com.jobportal.dto;

public class ErrorResponseDto {
//	private Object data;
	private String msgKey;
	private String message;

	public ErrorResponseDto(Object data, String msgKey, String message) {
		super();
		// this.data = data;
		this.msgKey = msgKey;
		this.message = message;
	}

//	public Object getData() {
//		return data;
//	}
//
//	public void setData(Object data) {
//		this.data = data;
//	}

	public String getMsgKey() {
		return msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResponseDto() {
		super();

	}

	public ErrorResponseDto(String msgKey, String message) {
		super();
		this.msgKey = msgKey;
		this.message = message;
	}

}
