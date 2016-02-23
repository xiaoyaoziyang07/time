package cn.amichina.timecomm.quota.timebaseplan;

public class TimeBasePolicy {

	private String policyId;
	private String policyName;
	private String startTime1;
	private String endTime1;
	private String startTime2;
	private String endTime2;
	private long upload1;
	private long download1;
	private long upload2;
	private long download2;
	private int mon;
	private int tues;
	private int wed;
	private int thur;
	private int fri;
	private int sat;
	private int sun;
	private int isactived;
	private int ruleType;
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getStartTime1() {
		return startTime1;
	}
	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}
	public String getEndTime1() {
		return endTime1;
	}
	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}
	public String getStartTime2() {
		return startTime2;
	}
	public void setStartTime2(String startTime2) {
		this.startTime2 = startTime2;
	}
	public String getEndTime2() {
		return endTime2;
	}
	public void setEndTime2(String endTime2) {
		this.endTime2 = endTime2;
	}
	public long getUpload1() {
		return upload1;
	}
	public void setUpload1(long upload1) {
		this.upload1 = upload1;
	}
	public long getDownload1() {
		return download1;
	}
	public void setDownload1(long download1) {
		this.download1 = download1;
	}
	public long getUpload2() {
		return upload2;
	}
	public void setUpload2(long upload2) {
		this.upload2 = upload2;
	}
	public long getDownload2() {
		return download2;
	}
	public void setDownload2(long download2) {
		this.download2 = download2;
	}
	public int getMon() {
		return mon;
	}
	public void setMon(int mon) {
		this.mon = mon;
	}
	public int getTues() {
		return tues;
	}
	public void setTues(int tues) {
		this.tues = tues;
	}
	public int getWed() {
		return wed;
	}
	public void setWed(int wed) {
		this.wed = wed;
	}
	public int getThur() {
		return thur;
	}
	public void setThur(int thur) {
		this.thur = thur;
	}
	public int getFri() {
		return fri;
	}
	public void setFri(int fri) {
		this.fri = fri;
	}
	public int getSat() {
		return sat;
	}
	public void setSat(int sat) {
		this.sat = sat;
	}
	public int getSun() {
		return sun;
	}
	public void setSun(int sun) {
		this.sun = sun;
	}
	public int getIsactived() {
		return isactived;
	}
	public void setIsactived(int isactived) {
		this.isactived = isactived;
	}
	public int getRuleType() {
		return ruleType;
	}
	public void setRuleType(int ruleType) {
		this.ruleType = ruleType;
	}
}
