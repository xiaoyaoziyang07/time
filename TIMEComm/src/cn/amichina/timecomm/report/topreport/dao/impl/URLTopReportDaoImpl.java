package cn.amichina.timecomm.report.topreport.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
 import cn.amichina.timecomm.report.model.TopReport;
import cn.amichina.timecomm.report.topreport.dao.TopReportDao;
import cn.amichina.timecomm.sys.model.QueryResult;
import cn.amichina.timecomm.util.DateUtil;
import cn.amichina.timecomm.util.JdbcTools;
@Repository
public class URLTopReportDaoImpl implements TopReportDao {
	@Override
	public QueryResult<TopReport> pageQueryTopReport(int startIndex,
			int pageSize, Date startDate, Date endDate) {
			QueryRunner runner =new QueryRunner();
			String sql ="select url as top,sum(downloadstream) as download ,sum(uploadstream) as upload, sum(persons) as total from pmreport.csr_store_1 where sj >= ? and sj <= ? group by url order by download desc limit ? offset ?";
			try {
				List<TopReport> list = runner.query(JdbcTools.getConnection(),sql, new BeanListHandler<TopReport>(TopReport.class),DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate),pageSize,startIndex);
				String sql_TotalRecord="select count(url) from  pmreport.csr_store_1 WHERE sj>=? and sj<=?";
				Long totalRecord =runner.query(JdbcTools.getConnection(), sql_TotalRecord, new ScalarHandler<Long>(),DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate));
			return new QueryResult<TopReport>(list,totalRecord);
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}
}
