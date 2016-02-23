package cn.amichina.timecomm.report.topreportbytop.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.amichina.timecomm.customerplan.dao.CustomerPlanDao;
import cn.amichina.timecomm.customerplan.model.CustomerPlan;
import cn.amichina.timecomm.report.model.TopReport;
import cn.amichina.timecomm.report.topreportbytop.service.TopReportByTopService;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;

import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
public class TopReportByTopAction extends ActionSupport {
	@Resource
	private TopReportByTopService topReportByTopService;
	@Resource
	private CustomerPlanDao customerPlanDao;
	private PageBean<TopReport> pageBean;
	private Date startDate;
	private Date endDate;
	private QueryInfo queryInfo;
	private String target;
	private String[] topReportHeaders;
	private String topReportTitle;
	private List<CustomerPlan> customerPlanList;
	private String customerPlanType;
	@Override
	public String execute() throws Exception {
		this.topReportHeaders=topReportByTopService.getTopReportHeaders(target);
		for (int i = 0; i < topReportHeaders.length; i++) {
			topReportHeaders[i]=getText(topReportHeaders[i]);
		}
		this.topReportTitle=getText(topReportByTopService.getTopReportTitle(target));
		this.customerPlanList=customerPlanDao.getCustomerPlansByTypesIs_V_T_N();
		return SUCCESS;
	}
	public String data() throws Exception {
		pageBean =topReportByTopService.pageQueryReport(queryInfo,startDate, endDate, target,customerPlanType);
		return SUCCESS;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public PageBean<TopReport> getPageBean() {
		return pageBean;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setQueryInfo(QueryInfo queryInfo) {
		this.queryInfo = queryInfo;
	}
	public String[] getTopReportHeaders() {
		return topReportHeaders;
	}
	public String getTopReportTitle() {
		return topReportTitle;
	}
	public String getTarget() {
		return target;
	}
	public List<CustomerPlan> getCustomerPlanList() {
		return customerPlanList;
	}
	public void setCustomerPlanType(String customerPlanType) {
		this.customerPlanType = customerPlanType;
	}
}