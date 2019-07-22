package com.itheima.domain;

public class User {
	private int id;
	private String username;
	private String birthday;
	private String email;
	public User() {
		super();
	}
	public User(int id, String username, String birthday, String email) {
		super();
		this.id = id;
		this.username = username;
		this.birthday = birthday;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", birthday=" + birthday + ", email=" + email + "]";
	}
}
