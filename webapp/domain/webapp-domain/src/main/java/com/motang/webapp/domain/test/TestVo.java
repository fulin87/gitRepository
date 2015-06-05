package com.motang.webapp.domain.test;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * just for test
 * @author motang
 * @since version1.0 
 */
public class TestVo {

	@NotBlank(message="E0001")
	private String id;
	@NotEmpty(message="E0001")
	private String name;
	@NotBlank(message="E0001")
	@Email(message="E0002")
	private String email;
	private Date date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
