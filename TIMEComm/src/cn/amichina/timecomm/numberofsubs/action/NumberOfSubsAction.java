package cn.amichina.timecomm.numberofsubs.action;

import java.util.Date;

import javax.annotation.Resource;

import cn.amichina.timecomm.customerplan.service.CustomerPlanService;

import com.opensymphony.xwork2.ActionSupport;

public class NumberOfSubsAction extends ActionSupport {
	@Resource
	private CustomerPlanService customerPlanService;
	private Date startDate;
	private Date endDate;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
 