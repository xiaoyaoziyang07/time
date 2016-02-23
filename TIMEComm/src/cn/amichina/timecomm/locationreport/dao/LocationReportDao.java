package cn.amichina.timecomm.locationreport.dao;

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
public class LocationReportDao {
	/**
	 * 获取Vlan 排名
	 * @return
	 */
	public List<Map<String, Object>> getTopLocationVlanList(Date startDate,Date endDate,int flowType){
		String sql =String.format("SELECT  stat2 AS vlan ,SUM(%s) AS traffic  FROM pmreport.t_stat_4_2 WHERE stat1>= ? AND stat1<= ? GROUP BY vlan ORDER BY traffic DESC LIMIT 10",getByCol(flowType));
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql, new MapListHandler(),DateUtil.date2DBDateAsLong(startDate),DateUtil.date2DBDateAsLong(endDate));
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	/**
	 * 
	 * 获取vLan
	 * @param startDate
	 * @param endDate
	 * @param vlanId
	 * @return 
	 */
	public List<Map<String, Object>> getLocationVlanByHourList(Date startDate,Date endDate,String vlanId){
		return getLocationVlanBySql(Long.parseLong( DateUtil.toDBStr(startDate)+"000000"),Long.parseLong(DateUtil.toDBStr(endDate)+"230000"), vlanId, "t_stat_4_1");
	}
	public List<Map<String, Object>> getLocationVlanByDayList(Date startDate,Date endDate,String vlanId){
		return getLocationVlanBySql(DateUtil.date2DBDateAsLong(startDate), DateUtil.date2DBDateAsLong(endDate), vlanId, "t_stat_4_2");
	}
	//
	private  List<Map<String, Object>> getLocationVlanBySql(Long startDate,Long endDate, String vlanId,String tableName){
		String sql =String.format("SELECT stat1 AS sj,stat4 AS upload,stat5 AS download FROM pmreport.%s WHERE stat1>= ? AND stat1<= ? AND stat2= ? ORDER BY stat1",tableName);
		try {
			QueryRunner runner = new QueryRunner();
			return runner.query(JdbcTools.getConnection(), sql,new MapListHandler(),startDate,endDate,vlanId);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	private String getByCol(int flowType){
		String byColName=null;
		if(flowType==0){
			byColName ="stat4+stat5";
		}else if(flowType==1){
			byColName ="stat4";
		}else if(flowType==2){
			byColName ="stat5";
		}
		return byColName;
	}
}
