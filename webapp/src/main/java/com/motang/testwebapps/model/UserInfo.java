package com.motang.testwebapps.model;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class UserInfo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "UserInfo";
	public static final String ALIAS_USER_ID = "userId";
	public static final String ALIAS_USERNAME = "username";
	public static final String ALIAS_PASSWORD = "password";
	public static final String ALIAS_BIRTH_DATE = "birthDate";
	public static final String ALIAS_SEX = "sex";
	public static final String ALIAS_AGE = "age";
	
	//date formats
	//public static final String FORMAT_BIRTH_DATE = DATE_FORMAT;
	
	//columns START
	private Long userId;
	private String username;
	private String password;
	private Date birthDate;
	private Integer sex;
	private Integer age;
	//columns END

	public UserInfo(){
	}

	public UserInfo(
		Long userId
	){
		this.userId = userId;
	}

	public void setUserId(Long value) {
		this.userId = value;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return this.username;
	}
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return this.password;
	}
//	public String getBirthDateString() {
//		return DateConvertUtils.format(getBirthDate(), FORMAT_BIRTH_DATE);
//	}
//	public void setBirthDateString(String value) {
//		setBirthDate(DateConvertUtils.parse(value, FORMAT_BIRTH_DATE,Date.class));
//	}
	
	public void setBirthDate(Date value) {
		this.birthDate = value;
	}
	
	public Date getBirthDate() {
		return this.birthDate;
	}
	public void setSex(Integer value) {
		this.sex = value;
	}
	
	public Integer getSex() {
		return this.sex;
	}
	public void setAge(Integer value) {
		this.age = value;
	}
	
	public Integer getAge() {
		return this.age;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId",getUserId())
			.append("Username",getUsername())
			.append("Password",getPassword())
			.append("BirthDate",getBirthDate())
			.append("Sex",getSex())
			.append("Age",getAge())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof UserInfo == false) return false;
		if(this == obj) return true;
		UserInfo other = (UserInfo)obj;
		return new EqualsBuilder()
			.append(getUserId(),other.getUserId())
			.isEquals();
	}
}

