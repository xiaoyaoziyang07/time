package cn.amichina.timecomm.customerplan.model;
/**
 * 
 * Create by 石磊  on 2015年9月23日 下午4:57:18
 *
 * 客户套餐策略
 * 
 */
public class CustomerPlan {
	/**
	 * 客户套餐Id
	 */
	private String customerPlanId;
	/**
	 * 客户套餐名称
	 */
	private String customerPlanName;
	/**
	 * 客户套餐类型
	 */
	private String customerPlanType;
	
	public String getCustomerPlanId() {
		return customerPlanId;
	}
	public void setCustomerPlanId(String customerPlanId) {
		this.customerPlanId = customerPlanId;
	}
	public String getCustomerPlanName() {
		return customerPlanName;
	}
	public void setCustomerPlanName(String customerPlanName) {
		this.customerPlanName = customerPlanName;
	}
	public String getCustomerPlanType() {
		return customerPlanType;
	}
	public void setCustomerPlanType(String customerPlanType) {
		this.customerPlanType = customerPlanType;
	}
	
	
}
