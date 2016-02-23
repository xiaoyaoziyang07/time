package cn.amichina.timecomm.quota.qfupplan.entity;

public class QFUPPlan {

	private String policyid;
	private String policyname;
	private Long quota;
	private Integer fup1_percent;
	private Long fup1_ul;
	private Long fup1_dl;
	private Integer fup2_percent;
	private Long fup2_ul;
	private Long fup2_dl;
	private Integer unit;
	
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
	public Long getQuota() {
		return quota;
	}
	public void setQuota(Long quota) {
		this.quota = quota;
	}
	public Integer getFup1_percent() {
		return fup1_percent;
	}
	public void setFup1_percent(Integer fup1_percent) {
		this.fup1_percent = fup1_percent;
	}
	public Long getFup1_ul() {
		return fup1_ul;
	}
	public void setFup1_ul(Long fup1_ul) {
		this.fup1_ul = fup1_ul;
	}
	public Long getFup1_dl() {
		return fup1_dl;
	}
	public void setFup1_dl(Long fup1_dl) {
		this.fup1_dl = fup1_dl;
	}
	public Integer getFup2_percent() {
		return fup2_percent;
	}
	public void setFup2_percent(Integer fup2_percent) {
		this.fup2_percent = fup2_percent;
	}
	public Long getFup2_ul() {
		return fup2_ul;
	}
	public void setFup2_ul(Long fup2_ul) {
		this.fup2_ul = fup2_ul;
	}
	public Long getFup2_dl() {
		return fup2_dl;
	}
	public void setFup2_dl(Long fup2_dl) {
		this.fup2_dl = fup2_dl;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
}
