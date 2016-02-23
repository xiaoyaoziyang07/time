package cn.amichina.timecomm.topvlan.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.util.DateUtil;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class UserByVlanDao {

	public List<String> vlanTop5listByDay(Date startDate,Date endDate) throws SQLException {
		String sql = "SELECT stat2,sum(stat3) from pmreport.t_stat_4_2 WHERE stat1>=? AND stat1<=? group by stat2 order by sum(stat3) DESC limit 5 offset 0";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>("stat2"),DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate));
	}

	public List<String> vlanTop5listByHour(Date startDate, Date endDate) throws SQLException {
		String sql = "select stat2,sum(stat3) from pmreport.t_stat_4_1 WHERE stat1>=? AND stat1<=? group by stat2 order by sum(stat3) DESC limit 5 offset 0";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>("stat2"),DateUtil.toDBDateTime(startDate)-120000,DateUtil.toDBDateTime(endDate)+110000);
	}

	public Map<Long, Map<String, Object>> getUserCountByDay(Date startDate, Date endDate, String vId) throws SQLException {
		String sql = "SELECT stat1,stat3 from pmreport.t_stat_4_2 where stat1>=? AND stat1<=? AND stat2=?";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(),sql,new KeyedHandler<Long>("stat1"),DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate),vId);
	}

	public Map<Long,Map<String, Object>> getUserCountByHour(Date startDate, Date endDate, String s) throws SQLException {
		String sql = "SELECT stat1,stat3 from pmreport.t_stat_4_1 where stat1>=? AND stat1<=? AND stat2=?";
		QueryRunner runner = new QueryRunner();
		System.out.println(DateUtil.toDBDateTime(startDate));
		System.out.println(DateUtil.toDBDateTime(endDate));
		return runner.query(JdbcTools.getConnection(),sql,new KeyedHandler<Long>("stat1"),DateUtil.toDBDateTime(startDate),DateUtil.toDBDateTime(endDate)+230000,s);
	}
}
