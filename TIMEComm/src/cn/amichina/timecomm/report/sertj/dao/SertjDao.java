package cn.amichina.timecomm.report.sertj.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.report.model.SertjReport;
import cn.amichina.timecomm.util.JdbcTools;
/**
 * 
 * Create by 石磊 on 2015年8月1日 下午11:28:57
 * 用户 时报日报流量包数据访问类
 */
@Repository
public class SertjDao {
	public SertjReport getDaySertjBySJAndServiceAndInternal_host(Date time ,String[] services,String internal_host){
		return getSertjReportByServicesAndInternal_Host(date2TableName("pmreport.daysertj",time),services,internal_host);
	}
	public SertjReport getHourSertjBySJAndServiceAndInternal_host(Date time ,String[] services,String internal_host) {
		return getSertjReportByServicesAndInternal_Host(date2TableName("pmreport.hoursertj",time),services,internal_host);
	}
	
	/**
	 * 
	 * 根据分组id和userid 查出该组上行流量和下行流量的和
	 * @throws SQLException 
	 * 
	 */
	private  SertjReport getSertjReportByServicesAndInternal_Host(String tablename,String[] services,String internal_host) {
			StringBuffer sb_sql  =new StringBuffer();
			sb_sql.append("SELECT sum(innum) as innum,sum(outnum) as  outnum FROM ");
			sb_sql.append(tablename);
			sb_sql.append(" where service in(");
			for (int i = 0; i < services.length; i++) {
				sb_sql.append('\'');
				sb_sql.append(services[i]);
				sb_sql.append('\'');
				if(i!=services.length-1){
					sb_sql.append(",");
				}
			}
			sb_sql.append(") and internal_host=?");
			QueryRunner runner = new QueryRunner();
			try {
				return runner.query(JdbcTools.getConnection(),sb_sql.toString(), 
						new BeanHandler<SertjReport>(SertjReport.class),internal_host);
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}
	private String date2TableName(String tableName ,Date time){
		StringBuffer sb_tableName  =new StringBuffer();
		sb_tableName.append(tableName);
		sb_tableName.append("_");
		SimpleDateFormat format =new SimpleDateFormat("yyyyMMdd");
		sb_tableName.append(format.format(time));
		return sb_tableName.toString(); 
	}
	
}
