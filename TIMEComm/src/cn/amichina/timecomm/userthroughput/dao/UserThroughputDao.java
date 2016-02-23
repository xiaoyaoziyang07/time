package cn.amichina.timecomm.userthroughput.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.userthroughput.model.MonitorUser;
import cn.amichina.timecomm.util.DateUtil;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class UserThroughputDao {
	public List<MonitorUser> getMonitorUserListByStatus(int status){
		String sql ="SELECT userid,status,isshow,starttime,endtime,time_stamp AS timeStamp,monitorid FROM pmconf.monitoruser WHERE status =? ORDER BY monitorid";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql, new BeanListHandler<MonitorUser>(MonitorUser.class),status);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	public MonitorUser getMonitorUserByMonitorIdAndStatus(Long monitorId,int status){
		String sql ="SELECT userid,status,isshow,starttime,endtime,time_stamp AS timeStamp,monitorid FROM pmconf.monitoruser WHERE monitorid =?  and  status =?";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql, new BeanHandler<MonitorUser>(MonitorUser.class),monitorId,status);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	public void updateMonitorUser(MonitorUser monitorUser){
		String sql="update pmconf.monitoruser set userid =?,status=?,isshow = ? ,starttime=?,endtime=?,time_stamp=(extract(epoch FROM now()))*1000 where monitorid =?";
		QueryRunner runner =new QueryRunner();
		try {
			runner.update(JdbcTools.getConnection(), sql,monitorUser.getUserId(), monitorUser.getStatus(),monitorUser.getIsShow(),monitorUser.getStartTime(),monitorUser.getEndTime(),monitorUser.getMonitorId());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	public Integer getMonitorUserCountByStatus(Integer status){
		return getMoitorUserColCountByColNameAndColVal("status",status.longValue()).intValue();
	}
	public Integer getMonitorUserCountByIsShow(Integer isshow){
		/*System.out.println();
		System.out.println(getMoitorUserColCountByColNameAndColVal("isshow",isshow));
		String  str =getMoitorUserColCountByColNameAndColVal("isshow",isshow).toString();
		return 0;*/
		
		return getMoitorUserColCountByColNameAndColVal("isshow",isshow.longValue()).intValue();
	}
	private <T> T getMoitorUserColCountByColNameAndColVal(String colName,T colVal){
		String sql=String.format("SELECT count(%s)FROM pmconf.monitoruser WHERE %s = ?",colName,colName);
		try {
			QueryRunner runner =new QueryRunner();
			return runner.query(JdbcTools.getConnection(),sql, new ScalarHandler<T>(),colVal);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addMoitorUser(){
		String sql ="INSERT INTO  pmconf.monitoruser (monitorid) VALUES(NEXTVAL('pmconf.seqidnum'))";
		//String sql ="INSERT into pmconf.monitoruser VALUES (?,?,?,?,?,(extract(epoch FROM now()))*1000)";
		try {
			QueryRunner runner =new QueryRunner();
			runner.insert(JdbcTools.getConnection(),sql, new ScalarHandler());
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	public List<String> getMonitorUserListByIsShow(int isShow){
		String sql ="SELECT userid FROM pmconf.monitoruser WHERE isshow =? AND userid IS NOT NULL ORDER BY monitorid";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql, new ColumnListHandler<String>(1),isShow);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	} 
	public List<Map<String,Object>> getTopUserAppsByUserId(Date startDate,String userId){
		String sql="SELECT app.name AS appname,t.service,SUM(t.dl_stream+t.ul_stream) AS sums FROM pmstaticrep.t_stat_6_2 t,  pmconf.sysservices AS app WHERE app.id=t.service AND t.time_stamp >= ? AND t.userid = ? GROUP BY t.service,app.name ORDER BY sums DESC LIMIT 5";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql, new MapListHandler(),DateUtil.toDBDateTime(startDate),userId);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	public List<Map<String,Object>> getUserAppUsageQuantity(Date startDate,String serviceId,String userId){
		String sql="select time_stamp  as sj,service,(dl_stream+ul_stream) as speed from pmstaticrep.t_stat_6_2 where time_stamp>= ? and service = ? and userid = ? ORDER BY time_stamp";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql, new MapListHandler(),DateUtil.toDBDateTime(startDate),serviceId,userId);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	public List<Map<String, Object>> getUserAppsUsageQuantity(Date time,String [] serviceId,String userId){
		StringBuilder sb =new StringBuilder();
		sb.append("SELECT service,sum(dl_stream+ul_stream) FROM  pmstaticrep.t_stat_6_2 WHERE time_stamp = ? AND userid = ? AND service in(");
		ayy2SQL(serviceId,sb);
		sb.append(" ) GROUP BY service "); 
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sb.toString(), new MapListHandler(),DateUtil.toDBDateTime(time),userId);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	/**
	 * 将字符串数组转换为sql语句 
	 * @param provtypes
	 * @param sb_Provtype
	 */
	public void ayy2SQL(String[] provtypes, 
			StringBuilder sb_Provtype) {
		for (int i = 0; i < provtypes.length; i++) {
			sb_Provtype.append('\'');
			sb_Provtype.append(provtypes[i]);
			sb_Provtype.append('\'');
			if(i!=provtypes.length-1){
				sb_Provtype.append(",");
			}
		}
	}
	public static void main(String[] args) {
		System.out.println(new Date(1449218775000l));
	}
}
