package cn.amichina.timecomm.decision.idmapping.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.decision.idmapping.MappingRecordBean;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class IDMappingDao {

	public Long getTotalNum(String userId,String ip,String planId) {
		
		String sql = "SELECT count(*) from (pmlogic.plobject A LEFT OUTER JOIN pmlogic.plobject_radius B ON A.userid=B.user_id) where A.isonline = 1";
		try{
			if(userId!=null&&!userId.equals("all")){
			sql += " and A.userId like '%" + userId.trim() + "%'";
		}
		if(ip!=null&&!ip.equals("all")){
			sql += " and (A.frame_ipv4 like '%" + ip.trim() + "%' or A.frame_ipv6 like '%" + ip.trim() + "%')";
		}
		if(planId!=null&&!planId.equals("all")){
			sql += " and B.nasportid like '%" + planId.trim() + "%'";
		}
			QueryRunner runner = new QueryRunner();
			return runner.query(JdbcTools.getConnection(), sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			throw new DataAccessException("数据访问异常！！！"+sql+"参数userId="+userId+",ip="+ip,e);
		}
	}

	public List<MappingRecordBean> getRecord(String userId,String ip,String planId,int pageNum,int pageSize){
		
		String sql = "SELECT A.userid,A.frame_ipv4,A.frame_ipv6,A.splanid, A.policyid,B.nasportid as vlan, to_char(B.login_time, '9999-99-99 99:99:99') as login_time FROM (pmlogic.plobject A LEFT OUTER JOIN pmlogic.plobject_radius B ON A.userid=B.user_id) WHERE A.isonline = 1";
		try {
			if(userId!=null&&!userId.equals("all")){
				sql += " and A.userId like '%" + userId.trim() + "%'";
			}
			if(ip!=null&&!ip.equals("all")){
				sql += " and (A.frame_ipv4 like '%" + ip.trim() + "%' or A.frame_ipv6 like '%" + ip.trim() + "%')";
			}
			if(planId!=null&&!planId.equals("all")){
				sql += " and B.nasportid like '%" + planId.trim() + "%'";
			}
			sql += " order by B.login_time desc limit "+ pageSize +" offset " + (pageNum-1)*pageSize;
			
			QueryRunner runner = new QueryRunner();
			
			return runner.query(JdbcTools.getConnection(), sql, new BeanListHandler<MappingRecordBean>(MappingRecordBean.class));
		} catch (SQLException e) {
			throw new DataAccessException("数据访问异常！！！"+sql+"userId="+userId+"ip="+ip+"pageNum="+pageNum+"pageSize="+pageSize);
		}
	}
}
