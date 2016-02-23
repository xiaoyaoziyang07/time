package cn.amichina.timecomm.quota.boost.entity;

public class BoostInfo {
	private String policyid;
	private String policyname;
	private String boosttype;
	private Long upload;
	private Long download;
	private Long duration;
	private Long claimtime;
	private Long validation;
	private Integer isactived;
	private Integer ismodify;
	private String content;
	

	@Override
	public String toString() {
		return "BoostInfo [policyid=" + policyid + ", policyname=" + policyname
				+ ", boosttype=" + boosttype + ", upload=" + upload
				+ ", download=" + download + ", duration=" + duration
				+ ", claimtime=" + claimtime + ", validation=" + validation
				+ ", isactived=" + isactived + ", ismodify=" + ismodify
				+ ", content=" + content + "]";
	}

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

	public String getBoosttype() {
		return boosttype;
	}

	public void setBoosttype(String boosttype) {
		this.boosttype = boosttype;
	}

	public Long getUpload() {
		return upload;
	}

	public void setUpload(Long upload) {
		this.upload = upload;
	}

	public Long getDownload() {
		return download;
	}

	public void setDownload(Long download) {
		this.download = download;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getClaimtime() {
		return claimtime;
	}

	public void setClaimtime(Long claimtime) {
		this.claimtime = claimtime;
	}

	public Long getValidation() {
		return validation;
	}

	public void setValidation(Long validation) {
		this.validation = validation;
	}

	public Integer getIsactived() {
		return isactived;
	}

	public void setIsactived(Integer isactived) {
		this.isactived = isactived;
	}

	public Integer getIsmodify() {
		return ismodify;
	}

	public void setIsmodify(Integer ismodify) {
		this.ismodify = ismodify;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
