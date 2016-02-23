package cn.amichina.timecomm.quota.model;

/**
 * 
 * Create by 石磊  on 2015年8月25日 下午1:48:37
 *
 *用户套餐
 */
public class Quota {
	/**
	 * 用户Id
	 */
	private String userid;
	/**
	 * 用户套餐Id
	 */
	private String serviceid;
	/**
	 * 所属套餐Id
	 */
	private String splanid;
	/**
	 * 到期时间
	 */
	private Long endtime;
	/**
	 * 套餐状态
	 */
	private Short status;
	/**
	 * 套餐总量
	 */
	private Long quotas;
	/**
	 * 套餐使用总量
	 */
	private Long totalusage;
	/**
	 * 套餐附加信息
	 */
	private Long remain1;
	/**
	 * 套餐附加信息
	 */
	private Long remain2;
	/**
	 * 套餐附加信息	
	 */
	private Long remain3;
	/**
	 * 套餐类型
	 */
	private String types;
	@Override
	public String toString() {
		return "Quota [userid=" + userid + ", serviceid=" + serviceid
				+ ", splanid=" + splanid + ", endtime=" + endtime + ", status="
				+ status + ", quotas=" + quotas + ", totalusage=" + totalusage
				+ ", remain1=" + remain1 + ", remain2=" + remain2
				+ ", remain3=" + remain3 + ", types=" + types + "]";
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getServiceid() {
		return serviceid;
	}
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	public String getSplanid() {
		return splanid;
	}
	public void setSplanid(String splanid) {
		this.splanid = splanid;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Long getQuotas() {
		return quotas;
	}
	public void setQuotas(Long quotas) {
		this.quotas = quotas;
	}
	public Long getTotalusage() {
		return totalusage;
	}
	public void setTotalusage(Long totalusage) {
		this.totalusage = totalusage;
	}
	public Long getRemain1() {
		return remain1;
	}
	public void setRemain1(Long remain1) {
		this.remain1 = remain1;
	}
	public Long getRemain2() {
		return remain2;
	}
	public void setRemain2(Long remain2) {
		this.remain2 = remain2;
	}
	public Long getRemain3() {
		return remain3;
	}
	public void setRemain3(Long remain3) {
		this.remain3 = remain3;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	
}
