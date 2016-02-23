package cn.amichina.timecomm.customerplan.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.amichina.timecomm.customerplan.service.CustomerPlanService;
import cn.amichina.timecomm.report.trendingreport.model.StackedAreaDateSet;
import cn.amichina.timecomm.vlan.service.VlanService;

import com.opensymphony.xwork2.ActionSupport;

public class CustomerPlanAction extends ActionSupport {
	@Resource
	private CustomerPlanService customerPlanService;
	@Resource
	private VlanService vlanService;
	private static final String JSONDATA = "toJson";
	private Date startDate;
	private Date endDate;
	private int type;
	private String vlan;
	private List<Object> hourReports;
	private StackedAreaDateSet dateSet;
	public String getVlan() {
		return vlan;
	}
	public void setVlan(String vlan) {
		this.vlan = vlan;
	}
	public void setType(int type) {
		this.type = type;
	}
	public  List<Object> getHourReports() {
		return hourReports;
	}
	@Override
	public String execute() throws Exception {
		this.hourReports = customerPlanService.getCustomerPlanHourFiles();
		if (startDate != null && endDate != null) {
				dateSet = customerPlanService.getCustomerPlanUsageQuantityStackedAreaDateSet(startDate, endDate, type,vlan);
			return JSONDATA;
		}
		return SUCCESS;
	}
	public CustomerPlanService getCustomerPlanService() {
		return customerPlanService;
	}
	public void setCustomerPlanService(CustomerPlanService customerPlanService) {
		this.customerPlanService = customerPlanService;
	}
	public VlanService getVlanService() {
		return vlanService;
	}
	public void setVlanService(VlanService vlanService) {
		this.vlanService = vlanService;
	}
	public static String getJsondata() {
		return JSONDATA;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public int getType() {
		return type;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public StackedAreaDateSet getDateSet() {
		return dateSet;
	}
	
}
 