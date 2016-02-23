package cn.amichina.timecomm.sys.model;

import java.util.List;

/**
 * 
 * Create by 石磊 on 2015年7月28日 下午10:57:05 
 * 查询结果实体
 * @param 分页元素
 */
public class QueryResult<T> {
	/**
	 * 分页元素集合
	 */
	private List<T> list;
	public QueryResult() {
	}

	public QueryResult(List<T> list, Long totalRecord) {
		super();
		this.list = list;
		this.totalRecord = totalRecord;
	}

	/**
	 * 总记录数
	 */
	private Long totalRecord;

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

}
