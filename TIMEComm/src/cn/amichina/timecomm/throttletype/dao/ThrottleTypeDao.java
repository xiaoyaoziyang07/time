package cn.amichina.timecomm.throttletype.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.util.DateUtil;
import cn.amichina.timecomm.util.JdbcTools;

/**
 * 
 * Create by 石磊  on 2015年11月12日 下午4:04:40
 *
 */
@Repository
public class ThrottleTypeDao {
	
	public List<String> getThrottlenameList(){
		String sql = "select throttlename from pmconf.syspolicy where throttlename<> '' order by throttlename";
		try {
			QueryRunner runner = new QueryRunner();
			return runner.query(JdbcTools.getConnection(), sql,
					new ColumnListHandler<String>(1));
		} catch (Exception e) {
			throw new DataAccessException("执行查询时发生异常! sql:" + sql, e);
		}
	}
	/**
	 * 查询得出对应时段购买提速Top 10的基础Plan
	 * @param startDate
	 * @param endDate
	 * @param throttleplanName
	 * @return
	 */
	public List<Map<String,Object>> getTopThrottleTypeListByAboutPlan(Date startDate ,Date endDate,String throttleplanName){
		return getTopThrottleTypeListFilter(startDate, endDate, "and aboutplan='"+throttleplanName+"'");
	}
	/**
	 * 查询得出对应时段购买提速Top 10的基础Plan
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String,Object>> getTopThrottleTypeList(Date startDate ,Date endDate){
		return getTopThrottleTypeListFilter(startDate, endDate, "");
	}
	/**
	 * 查询出的Plan求出各自所购买提速Plan的数量
	 * @param startDate
	 * @param endDate
	 * @param splanid
	 * @param throttleplanName
	 * @param aboutPlan
	 * @return
	 */
	public List<Map<String,Object>> getTopThrottleTypePlanListByAboutPlan(Date startDate,Date endDate,String splanid ,String throttleplanName,String aboutPlan)	{
		return getTopThrottleTypePlanListFilter(startDate,endDate,splanid,"and aboutplan='"+throttleplanName+"'");		
	}
	/**
	 * 查询出的Plan求出各自所购买提速Plan的数量
	 * @param startDate
	 * @param endDate
	 * @param splanid
	 * @return
	 */
	public List<Map<String,Object>> getTopThrottleTypePlanList(Date startDate,Date endDate,String splanid)	{
		return getTopThrottleTypePlanListFilter(startDate,endDate,splanid,"");		
	}

	private List<Map<String, Object>> getTopThrottleTypePlanListFilter(Date startDate,Date endDate,String splanid ,String whereSql) {
		String sql = String.format("SELECT aboutplan,sum(nums) as nums FROM pmreport2.t_stat_5 WHERE sj>=? AND sj<=? AND splanid =? AND types=2 %s group by aboutplan order by nums desc limit 5",whereSql);
		try {
			QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new MapListHandler(), DateUtil.toDBStrInteger(startDate),DateUtil.toDBStrInteger(endDate),splanid);
	} catch (Exception e) {
		throw new DataAccessException("执行查询时发生异常! sql:"+sql,e);
	}
	}
	private  List<Map<String,Object>> getTopThrottleTypeListFilter(Date startDate,Date endDate,String whereSql){
		String sql =String.format("SELECT splanid,sum(nums) as nums FROM pmreport2.t_stat_5 WHERE sj>= ? AND sj<= ? AND types = 2 %s group by splanid order by nums desc limit 10",whereSql);
		try {
				QueryRunner runner = new QueryRunner();
			return runner.query(JdbcTools.getConnection(), sql, new MapListHandler(), DateUtil.toDBStrInteger(startDate),DateUtil.toDBStrInteger(endDate));
		} catch (Exception e) {
			throw new DataAccessException("执行查询时发生异常! sql:"+sql,e);
		}
	}
}
