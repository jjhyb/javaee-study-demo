package com.itheima.domain;

public class Record {
	private String id;
	private int userId;
	private int type;
	private double money;
	private int targetUserId;
	private String tradeDate;
	public Record() {
		super();
	}
	public Record(String id, int userId, int type, double money, int targetUserId, String tradeDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.type = type;
		this.money = money;
		this.targetUserId = targetUserId;
		this.tradeDate = tradeDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(int targetUserId) {
		this.targetUserId = targetUserId;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	
}
