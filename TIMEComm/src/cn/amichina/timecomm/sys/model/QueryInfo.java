package cn.amichina.timecomm.sys.model;

/**
 * 
 * Create by 石磊 on 2015年7月28日 下午10:56:57 查询信息实体
 */
@SuppressWarnings("unused")
public class QueryInfo {
	/**
	 * 查询页码
	 */
	private Integer currentPage = 1;
	/**
	 * 每页记录数
	 */
	private Integer pageSize = 12;
	/**
	 * 起始索引
	 */
	private Integer startIndex;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public QueryInfo(Integer currentPage, Integer pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public QueryInfo() {
		super();
		this.pageSize=10;
	}

	public Integer getStartIndex() {
		return currentPage * this.pageSize - this.pageSize;
	}
}
