package com.yibo.bean;

public class Item {
	private int count;
	private double total;
	private Product pro;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//此为只读属性，只可以获取，不可以更改
	public double getTotal() {
		return pro.getShop_price()*count;
	}
	public Product getPro() {
		return pro;
	}
	public void setPro(Product pro) {
		this.pro = pro;
	}
	
}
