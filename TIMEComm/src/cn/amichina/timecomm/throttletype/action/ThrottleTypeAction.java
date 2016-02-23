package cn.amichina.timecomm.throttletype.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.amichina.timecomm.throttletype.dao.ThrottleTypeService;

import com.opensymphony.xwork2.ActionSupport;

public class ThrottleTypeAction extends ActionSupport {
	@Resource
	private ThrottleTypeService throttleTypeService;
	private Date startDate;
	private Date endDate;
	private String throttleplanName;
	private String data;
	private List<String> throttleplanNameList;
	@Override
	public String execute() throws Exception {
		this.throttleplanNameList =throttleTypeService.getThrottlenameList();
		return SUCCESS;
	}
	public String data() throws Exception {
		this.data=throttleTypeService.getTopThrottleTypeListOfPlans(startDate, endDate, throttleplanName).drawColchart();
		return "toJson";
	}
	public String getData() {
		return data;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setThrottleplanName(String throttleplanName) {
		this.throttleplanName = throttleplanName;
	}
	public List<String> getThrottleplanNameList() {
		return throttleplanNameList;
	}
}
 