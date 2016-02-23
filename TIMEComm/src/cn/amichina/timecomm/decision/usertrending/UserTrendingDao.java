package cn.amichina.timecomm.decision.usertrending;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class UserTrendingDao {

	public List<String> top10User(Long startDate,Long endDate) throws SQLException {
		String sql = "select internal_host,sum(allnum) as sums from pmreport.optimize2 where sj >=? and sj<=? group by internal_host order by sums desc limit 10";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public Map<Long, Map<String, Object>> getTrafficByDay(Long startDate,Long endDate,String userId) throws SQLException {
		String sql = "select sj,innum+outnum as sums from pmreport2.daysimpletj where sj >=? and sj<=? and internal_host='"+userId+"'";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new KeyedHandler<Long>("sj"), startDate,endDate);
	}

	public Map<Long, Map<String, Object>> getUploadByDay(Long startDate, Long endDate, String userId) throws SQLException {
		String sql = "select sj,outnum from pmreport2.daysimpletj where sj >=? and sj<=? and internal_host='"+userId+"'";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new KeyedHandler<Long>("sj"), startDate,endDate);
	}

	public Map<Long, Map<String, Object>> getDownloadByDay(Long startDate, Long endDate, String userId) throws SQLException {
		String sql = "select sj,innum from pmreport2.daysimpletj where sj >=? and sj<=? and internal_host='"+userId+"'";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new KeyedHandler<Long>("sj"), startDate,endDate);
	}

	public Map<Long, Map<String, Object>> getTrafficByHour(Long startDate,Long endDate, String userId) throws SQLException {
		String sql = "select sj,innum+outnum as sums from pmreport2.hoursimpletj where sj >=? and sj<=? and internal_host='"+userId+"'";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new KeyedHandler<Long>("sj"), startDate,endDate);
	}

	public Map<Long, Map<String, Object>> getUploadByHour(Long startDate,Long endDate, String userId) throws SQLException {
		String sql = "select sj,outnum from pmreport2.hoursimpletj where sj >=? and sj<=? and internal_host='"+userId+"'";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new KeyedHandler<Long>("sj"), startDate,endDate);
	}

	public Map<Long, Map<String, Object>> getDownloadByHour(Long startDate, Long endDate, String userId) throws SQLException {
		String sql = "select sj,innum from pmreport2.hoursimpletj where sj >=? and sj<=? and internal_host='"+userId+"'";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new KeyedHandler<Long>("sj"), startDate,endDate);
	}
}
