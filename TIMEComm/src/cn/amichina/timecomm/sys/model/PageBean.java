package cn.amichina.timecomm.sys.model;

import java.util.List;

/**
 * 
 * Create by 石磊 on 2015年7月28日 下午10:45:04 分页实体
 * 
 * @param 分页元素
 */
public class PageBean<T> {
	public PageBean() {
		super();
	}
	public PageBean(QueryInfo queryInfo,QueryResult<T>  queryResult) {
		super();
		this.setCurrentPage(queryInfo.getCurrentPage());
		this.setList(queryResult.getList());
		this.setPageSize(queryInfo.getPageSize());
		this.setTotalRecord(queryResult.getTotalRecord());
	}
	/**
	 * 当前页码
	 */
	private Integer currentPage;

	/**
	 * 总页数
	 */
	private Integer totalPage;
	/**
	 * 分页元素集合
	 */
	private List<T> list;
	/**
	 * 每页记录数
	 */
	private Integer pageSize;
	/**
	 * 上一页
	 */
	private Integer previousPage;
	/**
	 * 下一页
	 */
	private Integer nextPage;
	/**
	 * 总记录数
	 */
	private Long totalRecord;
	/**
	 * 滚动页码
	 */
	private Integer[] pageBar;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		if (pageSize != 0)
			this.totalPage = totalRecord % this.pageSize == 0 ? totalRecord.intValue()
					/ this.pageSize : totalRecord.intValue() / this.pageSize + 1;
		return totalPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public int getPreviousPage() {
		this.previousPage = this.currentPage - 1;
		return previousPage < 1 ? 1 : previousPage;
	}

	public int getNextPage() {
		this.nextPage = this.currentPage + 1;
		return nextPage >  getTotalPage() ?  getTotalPage() : nextPage;
	}

	public Integer getPage(Integer page) {
		return page > this.pageSize ? this.pageSize : page;
	}

	public Integer[] getPageBar() {
		int startPage;
		int endPage;
		if (this.totalPage <= 10) {
			startPage = 1;
			endPage = this.totalPage;
		} else {
			startPage = this.currentPage - 4;
			endPage = this.currentPage + 5;
			if (startPage < 1) {
				startPage = 1;
				endPage = 10;
			}
			if (endPage > this.totalPage) {
				endPage = this.totalPage;
				startPage = this.totalPage - 9;
			}
		}
		Integer arr[] = new Integer[endPage > 10 ? 10 : endPage];
		for (int i = startPage; i <= endPage; i++) {
			arr[i - startPage] = i;
		}
		this.pageBar = arr;
		return pageBar;
	}
}
