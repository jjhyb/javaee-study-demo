package com.itheima.domain;

import java.util.List;

public class PageBean<E> {
	//当前页数
	private int curPage;
	//每页的数据条数
	private int pageSize;
	//总页数
	private int totalPage;
	//总数据条数
	private int totalSize;
	//当前页的数据集合
	private List<E> list;
	
	public PageBean() {
		super();
	}
	public PageBean(int curPage, int pageSize, int totalPage, int totalSize, List<E> list) {
		super();
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.totalSize = totalSize;
		this.list = list;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
}
