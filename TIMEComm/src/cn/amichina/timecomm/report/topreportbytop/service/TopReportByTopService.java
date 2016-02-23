package cn.amichina.timecomm.report.topreportbytop.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.timecomm.report.model.TopReport;
import cn.amichina.timecomm.report.topreport.service.TopReportService;
import cn.amichina.timecomm.report.topreportbytop.dao.TopReportByTopDao;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
@Service
public class TopReportByTopService {
	@Resource
	private TopReportByTopDao topReportByTopDao;
	@Resource
	private TopReportByTopDao applicationTopReportByVLANDaoImpl;
	@Resource
	private TopReportService topReportService;
	public PageBean<TopReport> pageQueryReport(QueryInfo queryInfo,Date startDate,Date endDate,final String target,final String topId) throws Exception{
		if(topId!=null&&topId.equals("all")){
			return topReportService.pageQueryReport(queryInfo, startDate, endDate, target);
		}
		return new PageBean<TopReport>(queryInfo,topReportByTopDao.pageQueryTopReportByTop(queryInfo.getStartIndex(), queryInfo.getPageSize(), startDate, endDate,topId));
	}
	public String getTopReportTitle(String target) {
		return topReportService.getTopReportTitle(target);
	}
	public String[] getTopReportHeaders(String target) {
		return topReportService.getTopReportHeaders(target);
	}
}
