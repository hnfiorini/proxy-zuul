package com.mercadolibre.proxy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class LogEntity {
	@Id
	private Long id;
	@Column
	private String typeLog;
	@Column
	private Date createdAt;
	@Column
	private String message;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
