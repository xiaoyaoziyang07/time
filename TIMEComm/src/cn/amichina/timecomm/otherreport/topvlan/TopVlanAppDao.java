package cn.amichina.timecomm.otherreport.topvlan;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class TopVlanAppDao {
	
	public List<String> vlanTop10ListByAll(Long startDate, Long endDate) throws SQLException {
		String sql = "select vlan from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan is not null group by vlan order by sum(innum+outnum) desc limit 10";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<String> serviceListByAll(Long startDate, Long endDate) throws SQLException {
		String sql = "select distinct service from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service is not null and vlan in (select vlan from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan is not null group by vlan order by sum(innum+outnum) desc limit 10)";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,startDate,endDate);
	}

	public List<Object[]> getServiceByVlanByAll(Long startDate, Long endDate,String label) throws SQLException {
		String sql = "select service,sum(innum+outnum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan=? group by service order by traffic desc limit 5";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,label);
	}
	
	public Object[] getOtherByVlanByAll(Long startDate, Long endDate, String label) throws SQLException {
		String sql = "SELECT vlan,SUM(innum+outnum) FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? AND vlan=? AND service NOT IN (SELECT service FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? and vlan=? GROUP BY service ORDER BY sum(innum+outnum) DESC LIMIT 5) GROUP BY vlan";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayHandler(), startDate,endDate,label,startDate,endDate,label);
	}

	public List<String> vlanTop10ListByIn(Long startDate, Long endDate) throws SQLException {
		String sql = "select vlan,sum(innum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan is not null group by vlan order by traffic desc limit 10";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<String> serviceListByIn(Long startDate, Long endDate) throws SQLException {
		String sql = "select distinct service from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service is not null";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<Object[]> getAppByVlanByIn(Long startDate, Long endDate,String label) throws SQLException {
		String sql = "select service,sum(innum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan=? group by service order by traffic desc limit 5";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,label);
	}

	public Object[] getOtherByVlanByIn(Long startDate, Long endDate,String label) throws SQLException {
		String sql = "SELECT vlan,SUM(innum) FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? AND vlan=? AND service NOT IN (SELECT service FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? and vlan=? GROUP BY service ORDER BY sum(innum) DESC LIMIT 5) GROUP BY vlan";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayHandler(), startDate,endDate,label,startDate,endDate,label);
	}

	public List<String> vlanTop10ListByOut(Long startDate, Long endDate) throws SQLException {
		String sql = "select vlan,sum(outnum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan is not null group by vlan order by traffic desc limit 10";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<String> serviceListByOut(Long startDate, Long endDate) throws SQLException {
		String sql = "select distinct service from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service is not null";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<Object[]> getAppByVlanByOut(Long startDate, Long endDate,String label) throws SQLException {
		String sql = "select service,sum(outnum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and vlan=? group by service order by traffic desc limit 5";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,label);
	}

	public Object[] getOtherByVlanByOut(Long startDate, Long endDate,String label) throws SQLException {
		String sql = "SELECT vlan,SUM(outnum) FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? AND vlan=? AND service NOT IN (SELECT service FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? and vlan=? GROUP BY service ORDER BY sum(outnum) DESC LIMIT 5) GROUP BY vlan";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayHandler(), startDate,endDate,label,startDate,endDate,label);
	}

	public List<Object[]> getVlanByAppByAll(Long startDate, Long endDate,String appId) throws SQLException {
		String sql = "SELECT vlan,sum(innum+outnum) FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? AND service=? and vlan is not null GROUP BY vlan";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,appId);
	}

	public List<Object[]> getVlanByAppByIn(Long startDate, Long endDate,String serviceId) throws SQLException {
		String sql = "SELECT vlan,sum(innum) FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? AND service=? and vlan is not null GROUP BY vlan";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,serviceId);
	}

	public List<Object[]> getVlanByAppByOut(Long startDate, Long endDate,String serviceId) throws SQLException {
		String sql = "SELECT vlan,sum(outnum) FROM pmreport2.vcdaytj_sv WHERE sj>=? AND sj<=? AND service=? and vlan is not null GROUP BY vlan";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,serviceId);
	}

	public List<String> vlanTop10ListByAll(Long startDate, Long endDate,String serviceId) throws SQLException {
		String sql = "select vlan,sum(innum+outnum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service=? and vlan is not null group by vlan order by traffic desc limit 10";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,serviceId);
	}
	
	public List<String> vlanTop10ListByIn(Long startDate, Long endDate,String serviceId) throws SQLException {
		String sql = "select vlan,sum(innum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service=? and vlan is not null group by vlan order by traffic desc limit 10";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,serviceId);
	}
	
	public List<String> vlanTop10ListByOut(Long startDate, Long endDate,String serviceId) throws SQLException {
		String sql = "select vlan,sum(outnum) as traffic from pmreport2.vcdaytj_sv where sj>=? and sj<=? and service=? and vlan is not null group by vlan order by traffic desc limit 10";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,serviceId);
	}

}
