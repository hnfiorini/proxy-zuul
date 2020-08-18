package com.mercadolibre.proxy.dto;

import java.util.Date;

public class LogDTO {
	
	private String typeLog;
	private Date createdAt;
	private String message;
	
	public String getTypeLog() {
		return typeLog;
	}
	public void setTypeLog(String typeLog) {
		this.typeLog = typeLog;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
