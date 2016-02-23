package cn.amichina.timecomm.quota.topupvolumn;

public class TopUpVolumn {

	private String policyid;
	private String policyname;
	private long traffic;
	private boolean isactived;
	
	public String getPolicyid() {
		return policyid;
	}
	public void setPolicyid(String policyid) {
		this.policyid = policyid;
	}
	public String getPolicyname() {
		return policyname;
	}
	public void setPolicyname(String policyname) {
		this.policyname = policyname;
	}
	public long getTraffic() {
		return traffic;
	}
	public void setTraffic(long traffic) {
		this.traffic = traffic;
	}
	public boolean isIsactived() {
		return isactived;
	}
	public void setIsactived(boolean isactived) {
		this.isactived = isactived;
	}
}