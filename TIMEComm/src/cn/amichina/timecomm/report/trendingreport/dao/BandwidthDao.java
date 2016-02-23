package cn.amichina.timecomm.report.trendingreport.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.util.DateUtil;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class BandwidthDao {
	/**
	 * 获取前五个流量 降序
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String, Object>> getTop5ServiceByStartDateAndEndDate(
			Date startDate, Date endDate, Integer type) {
		/* 
		 * select B.name,v.service from pmreport.vchourtj v,pmconf.sysservices B
		 where v.service=B.id and v.sj >= ? and v.sj <= ? group by v.service,
		 B.name order by sum(v.innum+v.outnum) desc limit 5
		 String sql
		 ="select service from pmreport.vcdaytj where sj >= ? and sj <= ? group by service order by sum(innum+outnum) desc limit 5";
		 * */
		String sql = "select B.name,v.serrvice  from pmreport2.vcdaytj_sp v,pmconf.sysservices B  where v.serrvice=B.id and v.sj >= ? and v.sj <= ? group by v.serrvice, B.name order by sum("
				+ getGroupBy(type) + ") desc limit 10";
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql,
					new MapListHandler(), DateUtil.toDBStrInteger(startDate),
					DateUtil.toDBStrInteger(endDate));
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 获取每天用户使用的流量统计
	 * 
	 * @param startDate
	 * @param endDate
	 * @param service
	 * @return
	 */
	public List<Map<String, Object>> getBandwidthAvg(Date startDate,
			Date endDate, String service, Integer type) {
		String sql = "select sj,round(sum("
				+ getGroupBy(type)
				+ ")*8/1024/1024/86400,2) as Mbps from pmreport2.vcdaytj_sp where sj >= ? and sj<= ? and serrvice = ? group by sj order by sj";
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql,
					new MapListHandler(), DateUtil.toDBStrInteger(startDate),
					DateUtil.toDBStrInteger(endDate), service);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 获取每天流量统计平均数
	 * 
	 * @param startDate
	 * @param endDate
	 * @param services
	 * @return
	 */
	public List<Map<String, Object>> getTotalBandwidthAvgFilterTop5(
			Date startDate, Date endDate, String[] services, Integer type)  {
		StringBuilder sb_sql = new StringBuilder(
				"select sj,round(sum("
						+ getGroupBy(type)
						+ ")*8/1024/1024/86400,2) as Mbps from pmreport2.vcdaytj_sp where sj >= ? and sj<= ? ");
		if (services != null && services.length != 0) {
			sb_sql.append("and serrvice not in(");
			for (int i = 0; i < services.length; i++) {
				sb_sql.append("'");
				sb_sql.append(services[i]);
				sb_sql.append(i != services.length - 1 ? "'," : "'");
			}
			sb_sql.append(")");
		}
		sb_sql.append("  group by sj order by sj");
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sb_sql.toString(),
					new MapListHandler(), DateUtil.toDBStrInteger(startDate),
					DateUtil.toDBStrInteger(endDate));
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 获取小时段流量平均值
	 * 
	 * @param startDate
	 * @param endDate
	 * @param service
	 * @return
	 * @throws NumberFormatException 
	 */
	public List<Map<String, Object>> getBandwidthAvgHour(Date startDate,
			Date endDate, String service, Integer type){
		String sql = "select sj,round(sum("
				+ getGroupBy(type)
				+ ") * 8 /1024/1024/3600,2) as Mbps from pmreport2.vchourtj_sp  where sj >= ? and sj<= ? and service = ? group by sj order by sj";
			QueryRunner runner = new QueryRunner();
			try {
				return runner.query(
						JdbcTools.getConnection(),
						sql,
						new MapListHandler(),
						Long.parseLong(DateUtil.toDBStr(startDate) + "000000"),
						Long.parseLong(DateUtil.toDBStr(DateUtil.add(endDate,Calendar.DATE, 1)) + "000000"), service);
			} catch (NumberFormatException e) {
				throw new DataAccessException("查询数据转换参数时,发生异常!",e);
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}

	/**
	 * 获取获取小时段流量统计值
	 * 
	 * @param startDate
	 * @param endDate
	 * @param services
	 * @return
	 */
	public List<Map<String, Object>> getTotalBandwidthAvgHourFilterTop5(
			Date startDate, Date endDate, String[] services, Integer type) {
		StringBuilder sb_sql = new StringBuilder(
				"select sj,round(sum("
						+ getGroupBy(type)
						+ ")*8/1024/1024/3600,2) as Mbps from pmreport2.vchourtj_sp where sj >= ? and sj<= ? ");
		if (services != null && services.length != 0) {
			sb_sql.append("and service not in(");
			for (int i = 0; i < services.length; i++) {
				sb_sql.append("'");
				sb_sql.append(services[i]);
				sb_sql.append(i != services.length - 1 ? "'," : "'");
			}
			sb_sql.append(")");
		}
		sb_sql.append("  group by sj order by sj");
			QueryRunner runner = new QueryRunner();
			try {
				return runner.query(JdbcTools.getConnection(), sb_sql.toString(),
						new MapListHandler(),
						Long.parseLong(DateUtil.toDBStr(startDate) + "000000"),
						Long.parseLong(DateUtil.toDBStr(endDate) + "240000"));
			} catch (NumberFormatException e) {
				throw new DataAccessException(e);
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}

	/**
	 * 获取小时流量前五名 降序
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String, Object>> getTop5ServiceByStartDateAndEndDateHourOrder(
			Date startDate, Date endDate, Integer type)  {
		String sql = "select B.name,v.service  from pmreport2.vchourtj_sp v,pmconf.sysservices B  where v.service=B.id and v.sj >= ? and v.sj <= ? group by v.service, B.name order by sum(" + getGroupBy(type) + ") desc limit 5";
			QueryRunner runner = new QueryRunner();
			try {
				return runner.query(
						JdbcTools.getConnection(),
						sql,
						new MapListHandler(),
						Long.parseLong(DateUtil.toDBStrInteger(startDate)
								+ "000000"),
						Long.parseLong(DateUtil.toDBStrInteger(startDate)
								+ "000000"));
			} catch (NumberFormatException e) {
				throw new DataAccessException(e);
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}

	private String getGroupBy(Integer type) {
		String orderType = null;
		if (type == 0) {
			orderType = "innum+outnum";
		} else if (type == 1) {
			orderType = "innum";
		} else if (type == 2) {
			orderType = "outnum";
		} else {
			throw new IllegalArgumentException("排序类型只能为 0(上传+下载)、1(上传)、2(下载)");
		}
		return orderType;
	}
}
