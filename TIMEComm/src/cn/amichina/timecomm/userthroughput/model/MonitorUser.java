package cn.amichina.timecomm.userthroughput.model;

/**
 * 
 * Create by 石磊  on 2015年11月20日 下午1:49:29
 *
 *	监控用户实体
 */
public class MonitorUser {

	private String userId;
	private Integer status;
	private Integer isShow;
	private Long startTime;
	private Long endTime;
	private Long timeStamp;
	private Long monitorId;
	
	public Long getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "MonitorUser [userId=" + userId + ", status=" + status
				+ ", isShow=" + isShow + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", timeStamp=" + timeStamp
				+ ", monitorId=" + monitorId + "]";
	}
	
}
