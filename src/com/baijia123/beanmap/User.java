package com.baijia123.beanmap;

public class User {

	private String username;
	private String sex;
	private String address;
	private String mobile;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String sex, String address, String mobile) {
		super();
		this.username = username;
		this.sex = sex;
		this.address = address;
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", sex=" + sex + ", address="
				+ address + ", mobile=" + mobile + "]";
	}

}
