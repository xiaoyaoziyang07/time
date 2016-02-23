package cn.amichina.timecomm.report.plusrservice.model;

/**
 * 
 * Create by 石磊  on 2015年8月5日 下午3:35:12
 * 用户套餐实体
 *
 */
public class PlusrService {
	/**
	 * 套餐类型
	 */
	private String provtype;
	/**
	 * 套餐名称
	 */
	private String server_name;
	/**
	 * 套餐总数
	 */
	private Long total;
	/**
	 * 套餐使用百分率
	 */
	private Double ratio;
	@Override
	public String toString() {
		return "PlusrService [provtype=" + provtype + ", server_name="
				+ server_name + ", total=" + total + ", ratio=" + ratio + "]";
	}
	public Double getRatio() {
		return ratio;
	}
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
	public String getProvtype() {
		return provtype;
	}
	public void setProvtype(String provtype) {
		this.provtype = provtype;
	}
	public String getServer_name() {
		return server_name;
	}
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
}
