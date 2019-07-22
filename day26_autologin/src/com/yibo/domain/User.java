package com.yibo.domain;

import java.util.Date;

public class User {
	private String id;
	private String username;
	private String password;
	private String sex;
	private String nickname;
	private String email;
	private Date birthday;
	private String hobby;
	public User() {
		super();
	}
	public User(String id, String username, String password, String sex, String nickname, String email, Date birthday,
			String hobby) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.nickname = nickname;
		this.email = email;
		this.birthday = birthday;
		this.hobby = hobby;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", sex=" + sex + ", nickname="
				+ nickname + ", email=" + email + ", birthday=" + birthday + ", hobby=" + hobby + "]";
	}
	
}
