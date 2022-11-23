package com.greenart.ch1.User;

import java.util.Date;
import java.util.Objects;

public class BCUserDto {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String tel;
	private Date reg_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	@Override
	public String toString() {
		return "BCUserDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", tel=" + tel
				+ ", reg_date=" + reg_date + "]";
	}
	public BCUserDto(String id, String pwd, String name, String email, String tel, Date reg_date) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.reg_date = reg_date;
	}
	
	public BCUserDto() {}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, pwd, tel);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BCUserDto other = (BCUserDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(pwd, other.pwd) && Objects.equals(tel, other.tel);
	}
	
	
	
}