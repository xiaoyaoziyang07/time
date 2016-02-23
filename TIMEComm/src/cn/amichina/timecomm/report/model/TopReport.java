package cn.amichina.timecomm.report.model;
/**
 * 
 * Create by 石磊  on 2015年8月20日 下午3:38:47
 *
 * TopReport
 */
public class TopReport extends Traffic{
	/**
	 * 类型
	 */
	private String top;
	/**
	 * 总数
	 */
	private Long total;
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public Long getTotal() {
		return total;
	}
	
	public void setTotal(Long total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return super.toString()+"|TopReport [top=" + top + ", total=" + total + "]";
	}
	
}
