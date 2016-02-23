package cn.amichina.timecomm.quota.planbuilder.model;

public class PlanBuilder {
	private String planid;
	private String planname;
	private Short paymenttype;
	private Short plantype;
	private String policyid;
	private Short source;
	private String packageid;
	private Integer plannum;
	/*****************套餐附加属性*****************************/
	private Long tierbaseUL;
	private Long tierbaseDL;
	private Long timebaseUL;
	private Long timebaseDL;
	private Long boostDL;
	private Long boostUL;
	private Long qfupQuote;
	private Long topupTraffic;
	private String timepolicyid;
	private String topuppolicyid;
	private String qfuppolicyid;
	private String freeboostid;
	private String paidboost;
	private String tierpolicyid;
	private int ruleType;
	
	
	
	
	

	public int getRuleType() {
		return ruleType;
	}

	public void setRuleType(int ruleType) {
		this.ruleType = ruleType;
	}

	public Long getTopupTraffic() {
		return topupTraffic;
	}

	public void setTopupTraffic(Long topupTraffic) {
		this.topupTraffic = topupTraffic;
	}

	public Long getBoostDL() {
		return boostDL;
	}

	public void setBoostDL(Long boostDL) {
		this.boostDL = boostDL;
	}

	public Long getBoostUL() {
		return boostUL;
	}

	public void setBoostUL(Long boostUL) {
		this.boostUL = boostUL;
	}

	public String getTierpolicyid() {
		return tierpolicyid;
	}

	public void setTierpolicyid(String tierpolicyid) {
		this.tierpolicyid = tierpolicyid;
	}

	public String getFreeboostid() {
		return freeboostid;
	}

	public void setFreeboostid(String freeboostid) {
		this.freeboostid = freeboostid;
	}

	public String getPaidboost() {
		return paidboost;
	}

	public void setPaidboost(String paidboost) {
		this.paidboost = paidboost;
	}

	public String getTimepolicyid() {
		return timepolicyid;
	}

	public void setTimepolicyid(String timepolicyid) {
		this.timepolicyid = timepolicyid;
	}

	public String getTopuppolicyid() {
		return topuppolicyid;
	}

	public void setTopuppolicyid(String topuppolicyid) {
		this.topuppolicyid = topuppolicyid;
	}

	public String getQfuppolicyid() {
		return qfuppolicyid;
	}

	public void setQfuppolicyid(String qfuppolicyid) {
		this.qfuppolicyid = qfuppolicyid;
	}

	public String getPlanid() {
		return planid;
	}
	public Integer getPlannum() {
		return plannum;
	}

	public void setPlannum(Integer plannum) {
		this.plannum = plannum;
	}
	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public Short getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(Short paymenttype) {
		this.paymenttype = paymenttype;
	}


	public String getPolicyid() {
		return policyid;
	}

	public void setPolicyid(String policyid) {
		this.policyid = policyid;
	}


	public Short getPlantype() {
		return plantype;
	}

	public void setPlantype(Short plantype) {
		this.plantype = plantype;
	}

	public Short getSource() {
		return source;
	}

	public void setSource(Short source) {
		this.source = source;
	}


	public String getPackageid() {
		return packageid;
	}

	public void setPackageid(String packageid) {
		this.packageid = packageid;
	}
	/*****************套餐附加属性*****************************/

	public Long getTierbaseUL() {
		return tierbaseUL;
	}

	public void setTierbaseUL(Long tierbaseUL) {
		this.tierbaseUL = tierbaseUL;
	}

	public Long getTierbaseDL() {
		return tierbaseDL;
	}

	public void setTierbaseDL(Long tierbaseDL) {
		this.tierbaseDL = tierbaseDL;
	}

	public Long getTimebaseUL() {
		return timebaseUL;
	}

	public void setTimebaseUL(Long timebaseUL) {
		this.timebaseUL = timebaseUL;
	}

	public Long getTimebaseDL() {
		return timebaseDL;
	}

	public void setTimebaseDL(Long timebaseDL) {
		this.timebaseDL = timebaseDL;
	}

	public Long getQfupQuote() {
		return qfupQuote;
	}

	public void setQfupQuote(Long qfupQuote) {
		this.qfupQuote = qfupQuote;
	}
	
	

}
