package cn.amichina.timecomm.otherreport.topvlan;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.amichina.timecomm.model.application.Application;
import cn.amichina.timecomm.model.application.ApplicationService;
import cn.amichina.timecomm.util.DateUtil;

import com.opensymphony.xwork2.ActionSupport;

public class TopVlanAppAction extends ActionSupport{
	
	@Resource
	private ApplicationService applicationService;
	@Resource
	private TopVlanAppService topVlanAppService;

	private Date startDate;
	private Date endDate;
	private int type;
	private List<Application> appList;
	private String appId;
	private String data;

	@Override
	public String execute() throws Exception {
			appList = applicationService.listApp();
		return SUCCESS;
	}

	public String data() throws Exception {
			data = topVlanAppService.toJson(DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate),appId,type);
		return SUCCESS;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Application> getAppList() {
		return appList;
	}

	public void setAppList(List<Application> appList) {
		this.appList = appList;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
