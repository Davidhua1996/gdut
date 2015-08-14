package com.gdut.domain;

import java.util.List;

@SuppressWarnings("rawtypes")
public class PageBean {
	private int beginPageIndex; // 页码列表的开始索引（包含）

	// 指定的或是页面参数
	private int currentPage; // 当前页
	private int endPageIndex; // 页码列表的结束索引（包含）

	// 计算
	private int pageCount; // 总页数
	private int pageSize; // 每页显示多少条

	// 查询数据库
	private int recordCount; // 总记录数
	private List recordList; // 本页的数据列表

	public PageBean(int currentPage, int pageSize, int recordCount,
			List recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;

		// 计算总页码
		pageCount = (recordCount + pageSize - 1) / pageSize;

		// 计算 beginPageIndex 和 endPageIndex
		// >> 总页数不多于10页，则全部显示
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页数多于10页，则显示当前页附近的共10个页码
		else {
			// 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;
			// 当前面的页码不足4个时，则显示前10个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 当后面的页码不足5个时，则显示后10个页码
			if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1;
			}
		}
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public List getRecordList() {
		return recordList;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}
}
