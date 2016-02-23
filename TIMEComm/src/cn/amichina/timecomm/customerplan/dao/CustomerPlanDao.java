package cn.amichina.timecomm.customerplan.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.customerplan.model.CustomerPlan;
import cn.amichina.timecomm.util.DateUtil;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class CustomerPlanDao {
	/*
	 * SELECT SQL ：SELECT splanid AS customerPlanId , splanname AS customerPlanName , types AS customerPlanType  FROM pmconf.sysserviceplan WHERE types IN ('N','T','V') ORDER BY customerPlanName;
	 */
	public List<CustomerPlan> getCustomerPlansByTypesIs_V_T_N() {
		String sql ="SELECT splanid AS customerPlanId , splanname AS customerPlanName , types AS customerPlanType  FROM pmconf.sysserviceplan WHERE types IN ('N','T','V') ORDER BY customerPlanName";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql,new  BeanListHandler<CustomerPlan>(CustomerPlan.class));
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	/*
	 * SELECT SQL : select servicename from pmreport.vcdaytj where sj >= 20150812 and sj <= 20150812 and servicename is not null group by servicename order by sum(innum+outnum) desc limit 5 
	 * 
	 */
	public List<Map<String, Object>> getTop5UsageQuantityByIn(Date startDate, Date endDate,String vlanId)   {
		return  getTop5UsageQuantity(startDate,endDate,"sum(innum)",vlanId);
	}
	public List<Map<String, Object>> getTop5UsageQuantityByOut(Date startDate, Date endDate,String vlanId)   {
		return getTop5UsageQuantity(startDate,endDate,"sum(outnum)",vlanId);
	}
	public List<Map<String, Object>> getTop5UsageQuantityByTotal(Date startDate, Date endDate,String vlanId)   {
		return getTop5UsageQuantity(startDate,endDate,"sum(innum+outnum)",vlanId);
	}  
	
	private List<Map<String, Object>> getTop5UsageQuantity(Date startDate, Date endDate,String byOrder,String vlanId)  {
		if(DateUtil.eqDate(startDate, endDate)){
			return getTop5UsageQuantityByHour(startDate, endDate, byOrder, vlanId);
		}
		String sql =String.format("select servicename from pmreport2.vcdaytj_pv where sj >= ? and sj <= ? and servicename is not null %s group by servicename order by %s desc limit 10" ,getVlanSql(vlanId),byOrder);
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql, new MapListHandler(),DateUtil.toDBStrInteger(startDate),DateUtil.toDBStrInteger(endDate));
		} catch (Exception e) {
			throw new DataAccessException("执行查询时，发生异常 !sql:"+sql, e);
		}
	}
	/*
	 * SELECT SQL :select sj,sum(innum+outnum) as Mbps from pmreport.vcdaytj where sj >= 20150812 and sj<= 20150812 and servicename='V1001R100J' group by sj order by sj 
	 */
	public  Map<String, Object>  getCustomerPlanDayUsageQuantity(Date date,
			String customerPlanId,int by,String vlanId) {
		String tableName=null;
		if(DateUtil.eqDate(date, new Date())){
			tableName=" pmreport2.vchourtj_pv";
		}else{
			tableName="pmreport2.vcdaytj_pv";
		}
		String sql =String.format("select sum(%s) as total from %s where sj >= ? and sj<= ? and servicename=? %s",getByCol(by),tableName,getVlanSql(vlanId));
		QueryRunner runner = new QueryRunner();
		try {
			Integer intDate =DateUtil.toDBStrInteger(date);
			return runner.query(JdbcTools.getConnection(),sql, new MapHandler(),intDate,intDate,customerPlanId);
		} catch (Exception e) {
			throw new DataAccessException("执行查询时，发生异常 !sql:"+sql, e);
		}
	}
	/*
	 * SELECT SQL :select sj,sum(innum+outnum) as Mbps from pmreport.vcdaytj where sj >= 20150812 and sj<= 20150812 and servicename='V1001R100J' group by sj order by sj 
	 */
	public  Map<String, Object>  getCustomerPlanUsageQuantityDayFilterCustomerPlanIds(Date date,
			int by,String vlanId, String[] customerPlanIds) {
		String sql =String.format("select sum(%s) as total from pmreport2.vcdaytj_pv where sj >= ? and sj<= ? and ( servicename  not in(%s)  %s or servicename is null)",getByCol(by),getVarSqlByArray(customerPlanIds),getVlanSql(vlanId));
		QueryRunner runner = new QueryRunner();
		try {
			Integer intDate =DateUtil.toDBStrInteger(date);
			return runner.query(JdbcTools.getConnection(),sql, new MapHandler(),intDate,intDate);
		} catch (Exception e) {
			throw new DataAccessException("执行查询时，发生异常 !sql:"+sql, e);
		}
	}
	public List<Map<String, Object>> getCustomerPlanHourUsageQuantity(Date date,
			String customerPlanId,int by,String vlanId){
		String sql=String.format("select  sj ,sum(%s) as total from  pmreport2.vchourtj_pv where sj >= ? and sj<= ? and servicename=? GROUP BY sj",getByCol(by));
		QueryRunner runner = new QueryRunner();
		try {
			long startDate =Long.parseLong(DateUtil.toDBStr(date)+"000000");
			long endDate =Long.parseLong(DateUtil.toDBStr(date)+"230000");
			return runner.query(JdbcTools.getConnection(),sql, new MapListHandler(),startDate,endDate,customerPlanId);
		} catch (Exception e) {
			throw new DataAccessException("执行查询时，发生异常 !sql:"+sql, e);
		}
		//select  sj ,sum(innum) as total from pmreport.vchourtj where sj >= 20150812000000 and sj<= 20150812230000 GROUP BY sj
	}
	private String getVlanSql(String vlanId){
		if(vlanId!=null&&(!vlanId.trim().isEmpty())){
			return "and vlan='"+vlanId+"'";
		}
		return "";
	}
	private String getByCol(int by){
		String byColName=null;
		if(by==0){
			byColName ="innum+outnum";
		}else if(by==1){
			byColName ="innum";
		}else if(by==2){
			byColName ="outnum";
		}
		return byColName;
	}
	
	public List<Map<String,Object>> getCustomerPlanUsageQuantityHourFilterCustomerPlanIds(Date date,
			int by,String vlanId,String [] CustomerPlanIds){
		String sql=String.format("select  sj ,sum(%s) as total from  pmreport2.vchourtj_pv where sj >= ? and sj<= ? and (servicename  not in(%s) or servicename is null)   GROUP BY sj",getByCol(by),getVarSqlByArray(CustomerPlanIds));
		QueryRunner runner = new QueryRunner();
		try {
			long startDate =Long.parseLong(DateUtil.toDBStr(date)+"000000");
			long endDate =Long.parseLong(DateUtil.toDBStr(date)+"230000");
			return runner.query(JdbcTools.getConnection(),sql, new MapListHandler(),startDate,endDate);
		} catch (Exception e) {
			throw new DataAccessException("执行查询时，发生异常 !sql:"+sql, e);
		}
	}
	
	private String getVarSqlByArray(String[] customerPlanIds) {
		StringBuilder sb =new StringBuilder();
		for (int i = 0; i < customerPlanIds.length; i++) {
			sb.append("'");
			sb.append(customerPlanIds[i]);
			sb.append(i != customerPlanIds.length - 1 ? "'," : "'");
		}
		return sb.toString();
	}
	
	public List<Object> getCustomerPlanHourFiles(){
		String sql="SELECT stat1 as date FROM pmstaticrep.t_stat_2 order by date desc";
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql,new ColumnListHandler<Object>(1));
		} catch (Exception e) {
			throw new DataAccessException("执行查询时，发生异常 !sql:"+sql, e);
		}
	}
	
	public String getCustomerPlanHourPathByDate(String date) {
		String sql="SELECT stat2 as path  FROM pmstaticrep.t_stat_2 where stat1 =?";
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql,new ScalarHandler<String>(),Long.parseLong(date));
		} catch (Exception e) {
			throw new DataAccessException("执行查询时，发生异常 !sql:"+sql, e);
		}
	}
	public List<Map<String,Object>> getTop5UsageQuantityByHour(Date startDate,Date endDate,String byOrder,String vlanId){
		String sql =String.format("select servicename from pmreport2.vchourtj_pv where sj >= ? and sj <= ? and servicename is not null %s group by servicename order by %s desc limit 10" ,getVlanSql(vlanId),byOrder);
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql,new MapListHandler(),Long.parseLong(DateUtil.toDBStr(startDate)+"000000"),Long.parseLong(DateUtil.toDBStr(endDate)+"230000"));
		} catch (Exception e) {
			throw new DataAccessException("执行查询时，发生异常 !sql:"+sql, e);
		}
	}
	public List<Map<String,Object>> getTopCustomerPlan(Date startDate,Date endDate,int flowType){
		String sql =String.format("SELECT servicename,SUM(%s) AS traffic FROM pmreport2.vcdaytj_pv WHERE sj >= ? AND sj <= ? AND servicename IS NOT NULL GROUP BY servicename ORDER BY  traffic DESC LIMIT 10" ,getByCol(flowType));
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql,new MapListHandler(),Long.parseLong(DateUtil.toDBStr(startDate)),Long.parseLong(DateUtil.toDBStr(endDate)));
		} catch (Exception e) {
			throw new DataAccessException("执行查询时，发生异常 !sql:"+sql, e);
		}
	}
	public List<Map<String,Object>> getTopService(Date startDate,Date endDate,String customerPlanId,int flowType){
		String sql =String.format("SELECT servicename,SUM(%s) AS traffic FROM pmreport2.vcdaytj_pv WHERE sj >=? AND sj <=? AND vlan <>'' AND servicename = ? group by servicename Order by traffic desc limit 5" ,getByCol(flowType));
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql,new MapListHandler(),Long.parseLong(DateUtil.toDBStr(startDate)),Long.parseLong(DateUtil.toDBStr(endDate)),customerPlanId);
		} catch (Exception e) {
			throw new DataAccessException("执行查询时，发生异常 !sql:"+sql, e);
		}
	}
	
}
