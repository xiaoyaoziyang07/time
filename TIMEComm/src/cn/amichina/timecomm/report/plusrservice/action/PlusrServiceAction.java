package cn.amichina.timecomm.report.plusrservice.action;

import java.util.Date;

import javax.annotation.Resource;

import cn.amichina.timecomm.report.plusrservice.model.PlusrService;
import cn.amichina.timecomm.report.plusrservice.service.PlusrServiceService;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PlusrServiceAction extends ActionSupport {
	@Resource 
	private PlusrServiceService plusrServiceService;
	private Date startDate;
	private Date endDate;
	private PageBean<PlusrService>  grossAddPageBean;
	private PageBean<PlusrService>  suspendPageBean;
	private PageBean<PlusrService>  terminatePageBean;
	private PageBean<PlusrService>  newAdditionPageBean;
	private PageBean<PlusrService>  netGrossPageBean;
	private PageBean<PlusrService>  restorationPageBean;
	private String  stautsType;
	private PageBean<PlusrService>  pageBean;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public String data() throws Exception {
		QueryInfo queryInfo = new QueryInfo(1, 3);
		this.grossAddPageBean=plusrServiceService.pageQueryPlusrServiceGrossAdd(startDate,endDate,queryInfo);
		this.suspendPageBean=plusrServiceService.pageQueryPlusrServiceSuspend(startDate,endDate,queryInfo);
		this.terminatePageBean=plusrServiceService.pageQueryPlusrServiceTerminate(startDate,endDate,queryInfo);
		this.newAdditionPageBean=plusrServiceService.pageQueryPlusrServiceNewAddition(startDate,endDate,queryInfo);
		this.netGrossPageBean=plusrServiceService.pageQueryPlusrServiceNetGross(startDate,endDate,queryInfo);
		this.restorationPageBean=plusrServiceService.pageQueryPlusrServiceRestoration(startDate,endDate,queryInfo);
		return SUCCESS;
	}
	
	public String tabledata() throws Exception {
		QueryInfo queryInfo = new QueryInfo(1, 9);
		pageBean=plusrServiceService.pageQueryPlusrServiceByPlusrServiceName(stautsType,queryInfo,startDate,endDate);
		return SUCCESS;
	}
	public PageBean<PlusrService> getGrossAddPageBean() {
		return grossAddPageBean;
	}
	public PageBean<PlusrService> getSuspendPageBean() {
		return suspendPageBean;
	}
	public PageBean<PlusrService> getTerminatePageBean() {
		return terminatePageBean;
	}
	public PageBean<PlusrService> getNewAdditionPageBean() {
		return newAdditionPageBean;
	}
	public PageBean<PlusrService> getNetGrossPageBean() {
		return netGrossPageBean;
	}
	public PageBean<PlusrService> getRestorationPageBean() {
		return restorationPageBean;
	}
	public PageBean<PlusrService> getPageBean() {
		return pageBean;
	}
	public void setStautsType(String stautsType) {
		this.stautsType = stautsType;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
