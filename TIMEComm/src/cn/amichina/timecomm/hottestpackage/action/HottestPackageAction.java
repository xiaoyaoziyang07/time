package cn.amichina.timecomm.hottestpackage.action;

import java.util.Date;

import javax.annotation.Resource;

import cn.amichina.timecomm.customerplan.service.CustomerPlanService;

import com.opensymphony.xwork2.ActionSupport;

public class HottestPackageAction extends ActionSupport {
	@Resource
	private CustomerPlanService customerPlanService;
	private Date startDate;
	private Date endDate;
	private Integer flowType;
	private String data;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public String data() throws Exception {
		this.data=customerPlanService.getTopCustomerPlanByStartDateAndEndDateAndFlowType(startDate, endDate, flowType).drawColchart();
		return "toJson";
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}
	public String getData() {
		return data;
	}
}
 