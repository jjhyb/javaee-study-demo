package com.itheima.domain;

public class Bean {
	private String pname;
	private String cname;
	private String dname;
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	@Override
	public String toString() {
		return "Bean [pname=" + pname + ", cname=" + cname + ", dname=" + dname + "]";
	}
	
}
