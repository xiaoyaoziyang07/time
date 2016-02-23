/*package cn.amichina.timecomm.servicesgroup.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.exception.DaoException;
import cn.amichina.timecomm.group.model.ServiceGroup;
import cn.amichina.timecomm.servicesgroup.model.ServicesGroup;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class ServicesGroupDao {
	public List<ServiceGroup> list(){
		String sql="select * from pmconf.servicesgroup";
		try {
			QueryRunner runner =new QueryRunner();
			return runner.query(JdbcTools.getConnection(),sql, new BeanListHandler<ServiceGroup>(ServiceGroup.class));
		} catch (Exception e) {
			throw new DaoException(e);
		   }
		}
	public void add(ServicesGroup servicesGroup){
		String sql="insert into pmconf.servicesgroup values (nextval('pmconf.seq_servicesgroup'),?,?,?,?)";
		try {
			QueryRunner runner =new QueryRunner();
			Object[] params = {servicesGroup.getGroupname(),servicesGroup.getContent(),servicesGroup.getCreatetime(),servicesGroup.getType()};//初始化参数
			runner.update(JdbcTools.getConnection(), sql,  params);
			//(JdbcTools.getConnection(), sql, params);(JdbcTools.getConnection(),sql, params);
		} catch (Exception e) {
			throw new DaoException(e);
		   }
	}
	
    public void  del(Integer id){
    	String sql="delete from pmconf.servicesgroup where groupid=?";
    	try {
			QueryRunner runner =new QueryRunner();
			runner.update(JdbcTools.getConnection(),sql, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
    }
   public void  update(ServicesGroup servicesGroup){
	   String sql="update pmconf.servicesgroup set groupname=?   where groupid=?";
   	try {
			QueryRunner runner =new QueryRunner();
			Object[] params = {servicesGroup.getGroupname(),servicesGroup.getGroupid()};
			runner.update(JdbcTools.getConnection(),sql, params);
		} catch (Exception e) {
			throw new DaoException(e);
		}
      }
}
*/