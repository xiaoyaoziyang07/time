package cn.amichina.timecomm.report.model;

import java.util.Date;
/**
 * 
 * Create by 石磊  on 2015年8月20日 下午3:27:16
 * 
 * 协议组流量
 *
 */
public class SertjReport {
	/**
	 * 流量产生时间
	 */
	private Long sj;
	/**
	 * 协议组Id
	 */
	private String service;
	/**
	 * 用户Id
	 */
	private String internal_host;
	/**
	 * 上传流量
	 */
	private Long innum;
	/**
	 * 下载流量
	 */
	private Long outnum;
	/**
	 * 
	 */
	private Long serversign;
	private Long packageid;
	/**
	 * 显示时期
	 */
	private Date date;
	/**
	 * 流量总数
	 */
	private  Long total;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Sertj [sj=" + sj + ", service=" + service + ", internal_host="
				+ internal_host + ", innum=" + innum + ", outnum=" + outnum
				+ ", serversign=" + serversign + ", packageid=" + packageid
				+ "]";
	}
	public Long getSj() {
		return sj;
	}
	public void setSj(Long sj) {
		this.sj = sj;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getInternal_host() {
		return internal_host;
	}
	public void setInternal_host(String internal_host) {
		this.internal_host = internal_host;
	}
	public Long getInnum() {
		return innum;
	}
	public void setInnum(Long innum) {
		this.innum = innum;
	}
	public Long getOutnum() {
		return outnum;
	}
	public void setOutnum(Long outnum) {
		this.outnum = outnum;
	}
	public Long getServersign() {
		return serversign;
	}
	public void setServersign(Long serversign) {
		this.serversign = serversign;
	}
	public Long getPackageid() {
		return packageid;
	}
	public void setPackageid(Long packageid) {
		this.packageid = packageid;
	}
}
