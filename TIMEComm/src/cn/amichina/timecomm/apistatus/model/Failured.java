package cn.amichina.timecomm.apistatus.model;
/**
 * 
 * 错误日志类型总数
 * Create by 石磊  on 2015年8月20日 下午3:15:11
 *
 */
public class Failured {
	/**
	 * 日志类型
	 */
	private String provtype;
	/**
	 * 数量
	 */
	private Integer count;
	
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getProvtype() {
		return provtype;
	}
	public void setProvtype(String provtype) {
		this.provtype = provtype;
	}
	@Override
	public String toString() {
		return "Notelog [provtype=" + provtype + ", count=" + count + "]";
	}
	
}
