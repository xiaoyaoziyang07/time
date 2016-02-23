package cn.amichina.timecomm.topapp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.util.JdbcTools;

/**
 * 
 * @author 李阳    创建于2015年11月11日
 * 
 *      hottest app模块Dao组件
 */
@Repository
public class AppByVlanDao {
	
	QueryRunner runner = new QueryRunner();
	
	public List<String> appTop10ListByAll(Long startDate, Long endDate) throws SQLException {
		String sql = "select service from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service is not null group by service order by sum(innum+outnum) desc limit 10";
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<String> vlanListByAll(Long startDate, Long endDate) throws SQLException {
		String sql = "select distinct vlan from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan is not null and service in (select service from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service is not null group by service order by sum(innum+outnum) desc limit 10)";
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,startDate,endDate);
	}

	public List<Object[]> getVlanByAppByAll(Long startDate, Long endDate,String label) throws SQLException {
		String sql = "select vlan,sum(innum+outnum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service=? and vlan is not null group by vlan order by traffic desc limit 5";
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,label);
	}
	
	public Object[] getOtherByAppByAll(Long startDate, Long endDate, String label) throws SQLException {
		String sql = "SELECT service,SUM(innum+outnum) FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? AND service=? AND (vlan NOT IN (SELECT vlan FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? and vlan is not null and service=? GROUP BY vlan ORDER BY sum(innum+outnum) DESC LIMIT 5) or vlan is null) GROUP BY service";
		return runner.query(JdbcTools.getConnection(), sql, new ArrayHandler(), startDate,endDate,label,startDate,endDate,label);
	}

	public List<String> appTop10ListByIn(Long startDate, Long endDate) throws SQLException {
		String sql = "select service from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan is not null group by service order by sum(innum) desc limit 10";
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<String> vlanListByIn(Long startDate, Long endDate) throws SQLException {
		String sql = "select distinct vlan from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan is not null and service in (select service from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service is not null group by service order by sum(innum) desc limit 10)";
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,startDate,endDate);
		
	}

	public List<Object[]> getVlanByAppByIn(Long startDate, Long endDate,String label) throws SQLException {
		String sql = "select vlan,sum(innum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service=? and vlan is not null group by vlan order by traffic desc limit 5";
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,label);
	}

	public Object[] getOtherByAppByIn(Long startDate, Long endDate,String label) throws SQLException {
		String sql = "SELECT service,SUM(innum) FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? AND service=? AND (vlan NOT IN (SELECT vlan FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? and vlan is not null and service=? GROUP BY vlan ORDER BY sum(innum) DESC LIMIT 5) or vlan is null)GROUP BY service";
		return runner.query(JdbcTools.getConnection(), sql, new ArrayHandler(), startDate,endDate,label,startDate,endDate,label);
	}

	public List<String> appTop10ListByOut(Long startDate, Long endDate) throws SQLException {
		String sql = "select service from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan is not null group by service order by sum(outnum) desc limit 10";
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<String> vlanListByOut(Long startDate, Long endDate) throws SQLException {
		String sql = "select distinct vlan from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan is not null and service in (select service from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service is not null group by service order by sum(outnum) desc limit 10)";
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,startDate,endDate);
	}

	public List<Object[]> getVlanByAppByOut(Long startDate, Long endDate,String label) throws SQLException {
		String sql = "select vlan,sum(outnum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service=? and vlan is not null group by vlan order by traffic desc limit 5";
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,label);
	}

	public Object[] getOtherByAppByOut(Long startDate, Long endDate,String label) throws SQLException {
		String sql = "SELECT service,SUM(outnum) FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? AND service=? AND (vlan NOT IN (SELECT vlan FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? and vlan is not null and service=? GROUP BY vlan ORDER BY sum(outnum) DESC LIMIT 5) or vlan is null)  GROUP BY service";
		return runner.query(JdbcTools.getConnection(), sql, new ArrayHandler(), startDate,endDate,label,startDate,endDate,label);
	}

	public List<Object[]> getAppByVlanByAll(Long startDate, Long endDate,String vlanId) throws SQLException {
		String sql = "SELECT service,sum(innum+outnum) FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? AND vlan=? GROUP BY service";
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,vlanId);
	}

	public List<Object[]> getAppByVlanByIn(Long startDate, Long endDate,String vlanId) throws SQLException {
		String sql = "SELECT service,sum(innum) FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? AND vlan=? GROUP BY service";
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,vlanId);
	}

	public List<Object[]> getAppByVlanByOut(Long startDate, Long endDate,String vlanId) throws SQLException {
		String sql = "SELECT service,sum(outnum) FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? AND vlan=? GROUP BY service";
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,vlanId);
	}

	public List<String> appTop10ListByAll(Long startDate, Long endDate,String vlanId) throws SQLException {
		String sql = "select service,sum(innum+outnum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan=? group by service order by traffic desc limit 10";
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,vlanId);
	}
	
	public List<String> appTop10ListByIn(Long startDate, Long endDate,String vlanId) throws SQLException {
		String sql = "select service,sum(innum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan=? group by service order by traffic desc limit 10";
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,vlanId);
	}
	
	public List<String> appTop10ListByOut(Long startDate, Long endDate,String vlanId) throws SQLException {
		String sql = "select service,sum(outnum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan=? group by service order by traffic desc limit 10";
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,vlanId);
	}

}
