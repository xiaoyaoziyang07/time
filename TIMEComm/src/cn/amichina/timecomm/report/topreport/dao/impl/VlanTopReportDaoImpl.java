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
public class VlanTopReportDaoImpl implements TopReportDao {
	@Override
	public QueryResult<TopReport> pageQueryTopReport(int startIndex,
			int pageSize, Date startDate, Date endDate) {
			QueryRunner runner =new QueryRunner();
			String sql ="select vlan as top,sum(innum)/1024 as download, sum(outnum)/1024 as upload ,(sum(innum)+sum(outnum))/1024 as total from pmreport2.vcdaytj_pv where sj >= ? and sj <= ? and vlan is not null group by vlan order by download desc limit ? offset ?";
			List<TopReport> list;
			try {
				list = runner.query(JdbcTools.getConnection(),sql, new BeanListHandler<TopReport>(TopReport.class),DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate),pageSize,startIndex);
			String sql_TotalRecord="select count(vlan)  from pmreport2.vcdaytj_pv where sj >= ? and sj <= ? and vlan is not null";
			Long totalRecord =runner.query(JdbcTools.getConnection(), sql_TotalRecord, new ScalarHandler<Long>(),DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate));
			return new QueryResult<TopReport>(list,totalRecord);
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}

}