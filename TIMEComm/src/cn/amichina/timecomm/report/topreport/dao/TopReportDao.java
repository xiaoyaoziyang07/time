package cn.amichina.timecomm.report.topreport.dao;

import java.util.Date;

import cn.amichina.timecomm.report.model.TopReport;
import cn.amichina.timecomm.sys.model.QueryResult;
/**
 * 
 * Create by 石磊  on 2015年8月20日 下午4:10:15
 * TopReport
 *
 */
public interface TopReportDao {
QueryResult<TopReport> pageQueryTopReport(int startIndex, int pageSize,Date startDate,Date endDate) throws Exception;
}
