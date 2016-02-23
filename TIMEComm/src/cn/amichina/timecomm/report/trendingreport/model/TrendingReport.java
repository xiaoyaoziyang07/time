package cn.amichina.timecomm.report.trendingreport.model;
import java.util.Date;
/**
 * 
 * Create by 石磊  on 2015年8月20日 下午4:18:59
 *
 * 趋势图报表
 */
public class TrendingReport {
	/**
	 * 时间
	 */
	private Date time;
	/**
	 * 套餐
	 */
	private String service;
	/**
	 * 流量
	 */
	private Long traffic;
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Long getTraffic() {
		return traffic;
	}
	public void setTraffic(Long traffic) {
		this.traffic = traffic;
	}
	
}
