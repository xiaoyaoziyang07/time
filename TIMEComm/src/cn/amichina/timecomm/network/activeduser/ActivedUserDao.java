package cn.amichina.timecomm.network.activeduser;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class ActivedUserDao {
	
	public List<String> userTop10ListByAll(Long startDate, Long endDate) throws SQLException {
		String sql = "select userid from pmreport.optimize2_s where sj >=? and sj<=? group by userid order by sum(innum+outnum) desc limit 10";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<String> serviceListByAll(Long startDate, Long endDate) throws SQLException {
		String sql = "SELECT DISTINCT service from pmreport.optimize2_s WHERE sj>=? AND sj<=? AND userid IN (select userid from pmreport.optimize2_s where sj >=? and sj<=? group by userid order by sum(innum+outnum) desc limit 10)";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,startDate,endDate);
	}
	
	public List<Object[]> getServiceByUserByAll(Long startDate, Long endDate,String user) throws SQLException {
		String sql = "select service,sum(innum+outnum) as traffic from pmreport.optimize2_s where sj >=? and sj<=? and userid=? group by service order by traffic desc limit 5";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,user);
	}

	public Object[] getOtherByUserByAll(Long startDate, Long endDate,String user) throws SQLException {
		String sql = "SELECT userid,SUM(innum+outnum) FROM pmreport.optimize2_s WHERE sj>=? AND sj<=? AND userid=? AND service NOT IN (SELECT service FROM pmreport.optimize2_s WHERE sj>=? AND sj<=? and userid=? GROUP BY service ORDER BY sum(innum+outnum) DESC LIMIT 5) GROUP BY userid";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayHandler(), startDate,endDate,user,startDate,endDate,user);
	}

	public List<String> userTop10ListByIn(Long startDate, Long endDate) throws SQLException {
		String sql = "select userid from pmreport.optimize2_s where sj >=? and sj<=? group by userid order by sum(innum) desc limit 10";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<String> serviceListByIn(Long startDate, Long endDate) throws SQLException {
		String sql = "SELECT DISTINCT service from pmreport.optimize2_s WHERE sj>=? AND sj<=? AND userid IN (select userid from pmreport.optimize2_s where sj >=? and sj<=? group by userid order by sum(innum) desc limit 10)";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,startDate,endDate);
	}
	public List<Object[]> getServiceByUserByIn(Long startDate, Long endDate,String user) throws SQLException {
		String sql = "select B.name,sum(innum) as traffic from pmreport.optimize2_s A,pmconf.sysservices B where A.service= B.id and sj >=? and sj<=? and userid=? group by B.name order by traffic desc limit 5";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,user);
	}

	public Object[] getOtherByUserByIn(Long startDate, Long endDate,String user) throws SQLException {
		String sql = "SELECT userid,sum(innum) FROM pmreport.optimize2_s WHERE sj>=? AND sj<=? AND userid=? AND service NOT IN (SELECT service FROM pmreport.optimize2_s WHERE sj>=? AND sj<=? and userid=? GROUP BY service ORDER BY sum(innum) DESC LIMIT 5) GROUP BY userid";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayHandler(), startDate,endDate,user,startDate,endDate,user);
	}

	public List<String> userTop10ListByOut(Long startDate, Long endDate) throws SQLException {
		String sql = "select userid from pmreport.optimize2_s where sj >=? and sj<=? group by userid order by sum(outnum) desc limit 10";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<String> serviceListByOut(Long startDate, Long endDate) throws SQLException {
		String sql = "SELECT DISTINCT service from pmreport.optimize2_s WHERE sj>=? AND sj<=? AND userid IN (select userid from pmreport.optimize2_s where sj >=? and sj<=? group by userid order by sum(outnum) desc limit 10)";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,startDate,endDate);
	}
	
	public List<Object[]> getServiceByUserByOut(Long startDate, Long endDate,String user) throws SQLException {
		String sql = "select B.name,sum(outnum) as traffic from pmreport.optimize2_s A,pmconf.sysservices B where A.service=B.id and sj >=? and sj<=? and userid=? group by B.name order by traffic desc limit 5";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,user);
	}

	public Object[] getOtherByUserByOut(Long startDate, Long endDate,String user) throws SQLException {
		String sql = "SELECT userid,sum(outnum) FROM pmreport.optimize2_s WHERE sj>=? AND sj<=? AND userid=? AND service NOT IN (SELECT service FROM pmreport.optimize2_s WHERE sj>=? AND sj<=? and userid=? GROUP BY service ORDER BY sum(outnum) DESC LIMIT 5) GROUP BY userid";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayHandler(), startDate,endDate,user,startDate,endDate,user);
	}

	
}