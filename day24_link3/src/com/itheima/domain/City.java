package com.itheima.domain;

public class City {
	private int id;
	private String cname;
	private int pid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", cname=" + cname + ", pid=" + pid + "]";
	}
	
}
