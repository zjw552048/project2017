package com.z.entity.common;

/**
 * 分页查询封装对象
 * @author ZhangJiawei
 *
 */
public class Pager {
	private int pageIndex;
	private int pageSize;
	private int pageStart;
	
	public Pager(int pageIndex, int pageSize){
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.pageStart = (pageIndex-1)*pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	@Override
	public String toString() {
		return "Pager [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", pageStart=" + pageStart + "]";
	}
	
	
}
