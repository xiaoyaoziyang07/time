package cn.amichina.timecomm.apistatus.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.apistatus.model.Failured;
import cn.amichina.timecomm.apistatus.model.Notelog;
import cn.amichina.timecomm.sys.model.QueryResult;
import cn.amichina.timecomm.util.DateUtil;
import cn.amichina.timecomm.util.JdbcTools;
@Repository
public class NotelogDao {
	/**
	 * 查询所有错误日志总数
	 */
	public List<Failured> getCountFailuredByStartDateAndEndDateAndResultcodeNot0(Date startDate,Date endDate){
		String sql ="SELECT count(*),notelog.provtype  FROM pmlogic.notelog WHERE resultcode !='0' and time_stamp >= ? and time_stamp <= ? GROUP BY notelog.provtype";
			QueryRunner runner =new QueryRunner();
			try {
				return runner.query(JdbcTools.getConnection(),sql, new BeanListHandler<Failured>(Failured.class),startDate.getTime(),DateUtil.add(endDate, Calendar.DATE, 1).getTime());
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}
	/**
	 * 
	 * 根据日志错误类型 分页查询
	 * @param startIndex
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @param provtype
	 * @return
	 */
	public QueryResult<Notelog> pageQueryNotelogsByStartDateAndEndDateAndResultcodeNot0(int startIndex, int pageSize,Date startDate,Date endDate,String provtype){
		String sql ="SELECT service_id as user_id ,resultcode,details ,time_stamp  FROM pmlogic.notelog WHERE  resultcode!='0'  and time_stamp>=? and time_stamp <=? and provtype=?  limit ? offset ?";
		QueryRunner runner =new QueryRunner();
		List<Notelog> list =null;
		try {
			list= runner.query(JdbcTools.getConnection(),sql, new BeanListHandler<Notelog>(Notelog.class),startDate.getTime(),endDate.getTime(),provtype,pageSize,startIndex);
			String sql_TotalRecord="SELECT count(*) FROM pmlogic.notelog WHERE resultcode!='0'and time_stamp>=? and time_stamp <=? and provtype=?";
			Long totalRecord =runner.query(JdbcTools.getConnection(), sql_TotalRecord, new ScalarHandler<Long>(),startDate.getTime(),endDate.getTime(),provtype);
			QueryResult<Notelog> queryResult =new QueryResult<Notelog>(list, totalRecord);
			return queryResult;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	
}
