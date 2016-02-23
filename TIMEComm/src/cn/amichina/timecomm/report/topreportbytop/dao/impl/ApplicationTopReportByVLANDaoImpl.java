package cn.amichina.timecomm.report.topreportbytop.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.report.model.TopReport;
import cn.amichina.timecomm.report.topreportbytop.dao.TopReportByTopDao;
import cn.amichina.timecomm.sys.model.QueryResult;
import cn.amichina.timecomm.util.DateUtil;
import cn.amichina.timecomm.util.JdbcTools;
@Repository
public class ApplicationTopReportByVLANDaoImpl implements TopReportByTopDao {
	/*
	 * 
	 * 查询SQL  
		SELECT A.Service,B.name AS top,SUM(innum) AS download, SUM(outnum) AS upload,SUM(innum+outnum) AS total
		 FROM pmreport.vcdaytj A,pmconf.sysservices B
		 WHERE A.service = B.id AND sj >= ? AND sj <= ? AND A.servicename= ?
		GROUP BY service,B.name ORDER BY download DESC LIMIT ? OFFSET ?
		select count(service) from pmreport.vcdaytj where sj >= ? and sj <= ? and servicename =?
	 * 
	 * 
	 */
	@Override
	public QueryResult<TopReport> pageQueryTopReportByTop(int startIndex,
			int pageSize, Date startDate, Date endDate, String topId)
			throws Exception {
		String sql ="SELECT A.Service,B.name AS top,SUM(innum) AS download, SUM(outnum) AS upload,SUM(innum+outnum) AS total FROM pmreport.vcdaytj A,pmconf.sysservices B WHERE A.service = B.id AND sj >= ? AND sj <= ? AND A.servicename= ? GROUP BY service,B.name ORDER BY download DESC LIMIT ? OFFSET ?";
		QueryRunner runner =new QueryRunner();
		List<TopReport> list = runner.query(JdbcTools.getConnection(),sql, new BeanListHandler<TopReport>(TopReport.class),DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate),topId,pageSize,startIndex);
		String sql_TotalRecord="select count(service) from pmreport.vcdaytj where sj >= ? and sj <= ? and servicename =?";
		Long totalRecord =runner.query(JdbcTools.getConnection(), sql_TotalRecord, new ScalarHandler<Long>(),DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate),topId); 
		return new QueryResult<TopReport>(list, totalRecord);
	}
}
