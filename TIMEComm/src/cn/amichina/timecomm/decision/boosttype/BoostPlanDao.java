package cn.amichina.timecomm.decision.boosttype;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Component;

import cn.amichina.timecomm.util.JdbcTools;

@Component
public class BoostPlanDao {

	public List<BoostPlan> getAllBoostPlan() throws SQLException {
		String sql = "select splanid from pmconf.sysserviceplan where types = 'F' or types = 'P' order by types,splanid";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new BeanListHandler<BoostPlan>(BoostPlan.class));
	}

	public List<String> planTop10List(Long startDate, Long endDate) throws SQLException {
		String sql = "SELECT splanid FROM pmreport2.t_stat_5 WHERE sj>=? AND sj<=? AND types=1 group by splanid order by sum(nums) desc limit 10";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<String> boostTypeList(Long startDate, Long endDate) throws SQLException {
		String sql = "select distinct aboutplan from pmreport2.t_stat_5 where sj>=? and sj<=? and types=1";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<Object[]> getTypeByPlan(Long startDate, Long endDate,String plan) throws SQLException {
		String sql = "SELECT aboutplan,sum(nums) as nums FROM pmreport2.t_stat_5 WHERE sj>=? AND sj<=? AND splanid=? AND types=1 group by aboutplan order by nums desc limit 5;";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,plan);
	}

	public Object[] getOtherByPlan(Long startDate, Long endDate, String plan) throws SQLException {
		String sql = "SELECT splanid,SUM(nums) FROM pmreport2.t_stat_5 WHERE sj>=? AND sj<=? AND splanid=? AND aboutplan NOT IN (SELECT aboutplan FROM pmreport2.t_stat_5 WHERE sj>=? AND sj<=? and splanid=? GROUP BY aboutplan ORDER BY sum(nums) DESC LIMIT 5) GROUP BY splanid";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayHandler(), startDate,endDate,plan,startDate,endDate,plan);
	}

	public List<String> appTop10List(Long startDate, Long endDate,String boostPlanId) throws SQLException {
		String sql = "SELECT splanid FROM pmreport2.t_stat_5 WHERE sj>=? AND sj<=? AND aboutplan=? GROUP BY splanid order by sum(nums) desc limit 10";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,boostPlanId);
	}

	public List<Object[]> getTypeByBoostPlanId(Long startDate, Long endDate,String boostPlanId) throws SQLException {
		String sql = "SELECT splanid,sum(nums) FROM pmreport2.t_stat_5 WHERE sj>=? AND sj<=? AND aboutplan=? GROUP BY splanid";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,boostPlanId);
	}

}
