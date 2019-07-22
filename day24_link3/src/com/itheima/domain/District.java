package com.itheima.domain;

public class District {
	private int id;
	private String dname;
	private int cid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "District [id=" + id + ", dname=" + dname + ", cid=" + cid + "]";
	}
	
}
