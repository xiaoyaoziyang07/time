package cn.amichina.timecomm.decision.idmapping;

public class MappingRecordBean {

	private String userid;
	private String frame_ipv4;
	private String frame_ipv6;
	private String splanid;
	private Integer policyid;
	private String vlan;
	private String login_time;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFrame_ipv4() {
		return frame_ipv4;
	}
	public void setFrame_ipv4(String frame_ipv4) {
		this.frame_ipv4 = frame_ipv4;
	}
	public String getFrame_ipv6() {
		return frame_ipv6;
	}
	public void setFrame_ipv6(String frame_ipv6) {
		this.frame_ipv6 = frame_ipv6;
	}
	public String getSplanid() {
		return splanid;
	}
	public void setSplanid(String splanid) {
		this.splanid = splanid;
	}
	public Integer getPolicyid() {
		return policyid;
	}
	public void setPolicyid(Integer policyid) {
		this.policyid = policyid;
	}
	public String getVlan() {
		return vlan;
	}
	public void setVlan(String vlan) {
		this.vlan = vlan;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	
//	@Override
//	public String toString() {
//		return "{userid:" + userid + ", frame_ipv4:"
//				+ frame_ipv4 + ", frame_ipv6:" + frame_ipv6 + ", spanid:"
//				+ spanid + ", policyid:" + policyid + ", vlan:" + vlan
//				+ ", login_time:" + login_time + "}";
//	}
}
