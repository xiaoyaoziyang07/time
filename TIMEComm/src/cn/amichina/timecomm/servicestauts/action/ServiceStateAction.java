package cn.amichina.timecomm.servicestauts.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.amichina.timecomm.report.plusrservice.plusrstatistics.model.PlusrServiceStatistics;
import cn.amichina.timecomm.report.plusrservice.plusrstatistics.service.PlusrServiceStatisticsService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ServiceStateAction extends ActionSupport {
	@Resource
	private PlusrServiceStatisticsService plusrServiceStatisticsService;
	private List<PlusrServiceStatistics> list;
	private Date startDate;
	private Date endDate;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public String serviceStatus() throws Exception {
		list = plusrServiceStatisticsService.getPlusrServiceStatisticesByStartDateAndEndDate(startDate,endDate);
		return SUCCESS;
	}
	
	public List<PlusrServiceStatistics> getList() {
		return list;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}