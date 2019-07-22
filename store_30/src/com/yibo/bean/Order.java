package com.yibo.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Order {
	private String oid;
	private String name;
	private String address;
	private String telephone;
	private double total;
	private String ordertime;
	private Integer state;
	private Map<String,Orderitem> map = new HashMap<>();
	private User user;
	private Collection<Orderitem> coll;
	public Collection<Orderitem> getColl() {
		return map.values();
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Map<String, Orderitem> getMap() {
		return map;
	}
	public void setMap(Map<String, Orderitem> map) {
		this.map = map;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}