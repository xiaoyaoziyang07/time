package cn.amichina.timecomm.report.sertj.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import cn.amichina.timecomm.group.dao.ServiceGroupDao;
import cn.amichina.timecomm.group.model.ServiceGroup;
import cn.amichina.timecomm.report.model.SertjReport;
import cn.amichina.timecomm.report.sertj.service.SertjService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SertjReportAction extends ActionSupport {
	@Resource
	private SertjService sertjService;
	@Resource
	private ServiceGroupDao serviceGroupDao;
	private Date startDate;
	private Date endDate;
	private String servicesId;
	private List<ServiceGroup> groupList;
	private List<Map<ServiceGroup, SertjReport>> rows;
	private Long totalAll =0l;
	public Long getTotalAll() {
		return totalAll;
	}
	public void setTotalAll(Long totalAll) {
		this.totalAll = totalAll;
	}
	@Override
	public String execute() throws Exception {
		groupList = serviceGroupDao.serviceGroupList();
		//添加统计组
		if (startDate != null && endDate != null && servicesId != null) {
			rows = sertjService.getSertjReports(groupList, servicesId,startDate,endDate);
			for (Map<ServiceGroup, SertjReport> row : rows) {
				ServiceGroup serviceGroup =null;
				for(ServiceGroup sg : groupList){
					sg.equals("total");
					serviceGroup =sg;
				}
				SertjReport  tosj =row.get(serviceGroup);
				if(tosj!=null){
					Long tmp_ =tosj.getTotal();
					if(tmp_!=null){
						tmp_+=totalAll;
						}
				}
			}
		}
		return SUCCESS;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<ServiceGroup> getGroupList() {
		return groupList;
	}
	public List<Map<ServiceGroup, SertjReport>> getRows() {
		return rows;
	}
	public void setServicesId(String servicesId) {
		this.servicesId = servicesId;
	}
	public String getServicesId() {
		return servicesId;
	}
}