package cn.amichina.timecomm.quota.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.quota.model.Quota;
import cn.amichina.timecomm.util.JdbcTools;
@Repository
public class QuotaDao {
	/**
	 * 根据用户ID查询用户套餐信息
	 * @param userId
	 * @return 用户套餐信息 
	 */
	public Quota getQuotaByUserId(String userId) throws Exception{
		QueryRunner runner =new QueryRunner();
		String sql ="SELECT pj.userid,pj.serviceid,pj.endtime,pj.splanid ,pj.status,"
				+ "sp.quotas,pj.totalusage,pj.remain1,pj.remain2,pj.remain3,sp.types "
				+ "FROM pmlogic.plobject pj INNER JOIN pmconf.sysserviceplan sp  ON pj.splanid=sp.splanid WHERE pj.userid=?";
			return runner.query(JdbcTools.getConnection(), sql,new BeanHandler<Quota>(Quota.class),userId);
	}
}
