package com.itheima.domain;

public class City {
	private int pid;
	private String name;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "City [pid=" + pid + ", name=" + name + "]";
	}
	
}
