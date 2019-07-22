package com.yibo.bean;

import java.util.ArrayList;
import java.util.List;

public class Orderitem {
	private String itemid;
	private int count;
	private double subtotal;
	//表示这个订单项属于哪个订单
	private Order order;
	//表示这个订单项有哪个商品
	private Product pro;
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getPro() {
		return pro;
	}
	public void setPro(Product pro) {
		this.pro = pro;
	}
	
}