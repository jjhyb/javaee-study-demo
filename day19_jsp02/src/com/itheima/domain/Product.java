package com.itheima.domain;

public class Product {
	private int pid;
	private String pname;
	private double shop_price;
	private String pimage;
	public Product() {
		super();
	}
	public Product(int pid, String pname, double shop_price, String pimage) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.shop_price = shop_price;
		this.pimage = pimage;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getShop_price() {
		return shop_price;
	}
	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", shop_price=" + shop_price + ", pimage=" + pimage + "]";
	}
	
	
	
}
