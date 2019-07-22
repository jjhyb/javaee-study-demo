package com.yibo.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Car {
	private double total_price;
	//这个map集合key为购物车项的商品pid，value为购物车项,因为一个购物车对应多个购物车项
	private Map<String,Item> map = new HashMap<>();
	private Collection<Item> coll;
	public double getTotal_price() {
		//直接迭代Map集合才是最优的选择
		//先定义一个计数器用于总价计算
		double sum = 0;
		Set<Entry<String, Item>> entrySet = map.entrySet();
		for(Entry<String, Item> en : map.entrySet()){
			sum += en.getValue().getTotal();
		}
		/*
		 * 这样写，购物车的商品总金额会显示不出来
		if(coll != null){	//这行代码不加的话，会抛空指针异常
			for (Item item : coll) {
				total_price += item.getTotal();
			}
		}*/
		return sum;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public Map<String, Item> getMap() {
		return map;
	}
	public void setMap(Map<String, Item> map) {
		this.map = map;
	}
	public Collection<Item> getColl() {
		return map.values();
	}
}
