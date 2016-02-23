package cn.amichina.timecomm.report.topreport.action;

import java.util.Date;

import javax.annotation.Resource;

import cn.amichina.timecomm.report.model.TopReport;
import cn.amichina.timecomm.report.topreport.service.TopReportService;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;

import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
public class TopReportAction extends ActionSupport {
	@Resource
	private TopReportService topReportService;
	private Date startDate;
	private Date endDate;
	private QueryInfo queryInfo;
	private String target;
	private String[] topReportHeaders;
	private String topReportTitle;
	@Override
	public String execute() throws Exception {
		this.topReportHeaders=topReportService.getTopReportHeaders(target);
		for (int i = 0; i < topReportHeaders.length; i++) {
			topReportHeaders[i]=getText(topReportHeaders[i]);
		}
		this.topReportTitle=getText(topReportService.getTopReportTitle(target));
		return SUCCESS;
	}
	public String data() throws Exception {
		pageBean =topReportService.pageQueryReport(queryInfo,startDate, endDate, target);
		return SUCCESS;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	private PageBean<TopReport> pageBean;
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
}