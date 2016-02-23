package cn.amichina.timecomm.report.topreportbytop.dao;

import java.util.Date;

import cn.amichina.timecomm.report.model.TopReport;
import cn.amichina.timecomm.sys.model.QueryResult;


public interface TopReportByTopDao{
	QueryResult<TopReport> pageQueryTopReportByTop(int startIndex, int pageSize,Date startDate,Date endDate,String topId) throws Exception;
}
