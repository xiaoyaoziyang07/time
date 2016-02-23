package cn.amichina.timecomm.network.hottestsites;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class HottestSitesDao {

	private QueryRunner runner = new QueryRunner();
	
	public List<String> top10Sites(Long startDate, Long endDate) throws SQLException {
		String sql = "select url from pmreport2.rpt_url where sj >=? and sj<=? group by url order by sum(nums) desc limit 10";
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate);
	}

	public List<Object[]> getVlanData(Long startDate, Long endDate,String site) throws SQLException {
		String sql = "select vlan,sum(nums) as cnt from pmreport2.rpt_url where sj >=? and sj<=? and url=? group by vlan order by cnt desc limit 5";
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,site);
	}

	public List<String> vlanList(Long startDate, Long endDate) throws SQLException {
		String sql = "select distinct vlan from pmreport2.rpt_url where sj>=? and sj<=? and url in (select url from pmreport2.rpt_url where sj >=? and sj<=? group by url order by sum(nums) desc limit 10)";
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,startDate,endDate);
	}

	public Object[] getOtherVlanData(Long startDate, Long endDate, String site) throws SQLException {
		String sql = "SELECT url,sum(nums) FROM pmreport2.rpt_url WHERE sj>=? AND sj<=? AND url=? AND( vlan NOT IN (select vlan from pmreport2.rpt_url where sj>=? and sj<=? and vlan is not null and url=? group by vlan order by sum(nums) desc limit 5) or vlan is null) GROUP BY url";
		return runner.query(JdbcTools.getConnection(), sql, new ArrayHandler(), startDate,endDate,site,startDate,endDate,site);
	}

	public List<String> top10SitesByVlan(Long startDate, Long endDate,String vlanId) throws SQLException {
		String sql = "select url from pmreport2.rpt_url where sj >=? and sj<=? and vlan=? group by url order by sum(nums) desc limit 10";
		return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>(), startDate,endDate,vlanId);
	}

	public List<Object[]> getDataByVlanId(Long startDate, Long endDate,String vlanId) throws SQLException {
		String sql = "SELECT url,sum(nums) FROM pmreport2.rpt_url WHERE sj>=? AND sj<=? AND vlan=? GROUP BY url";
		return runner.query(JdbcTools.getConnection(), sql, new ArrayListHandler(), startDate,endDate,vlanId);
	}

}
