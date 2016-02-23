package cn.amichina.timecomm.quota.tierbaseplan;

public class TierPolicy {

	private String policyid;
	private String policyname;
	private long upload;
	private long download;
	private boolean isactived;
	private double createtime;
	
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
	public long getUpload() {
		return upload;
	}
	public void setUpload(long upload) {
		this.upload = upload;
	}
	public long getDownload() {
		return download;
	}
	public void setDownload(long download) {
		this.download = download;
	}
	public boolean isIsactived() {
		return isactived;
	}
	public void setIsactived(boolean isactived) {
		this.isactived = isactived;
	}
	public double getCreatetime() {
		return createtime;
	}
	public void setCreatetime(double createtime) {
		this.createtime = createtime;
	}
}
