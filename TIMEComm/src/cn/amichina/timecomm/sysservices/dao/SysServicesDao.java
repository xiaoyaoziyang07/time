package cn.amichina.timecomm.sysservices.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.sys.model.QueryResult;
import cn.amichina.timecomm.sysservices.model.SysServices;
import cn.amichina.timecomm.util.JdbcTools;
@Repository
public class SysServicesDao {
	public QueryResult<SysServices> listSysServices(int startIndex, int pageSize) throws Exception{
		String sql="select name from pmconf.sysservices limit ? offset ?";
		QueryRunner runner =new QueryRunner();
		List<SysServices> list=null;
		list=runner.query(JdbcTools.getConnection(),sql, new BeanListHandler<SysServices>(SysServices.class),startIndex,pageSize);
		String sql_TotalRecord="select count(*) from pmconf.sysservices";
		Long totalRecord =runner.query(JdbcTools.getConnection(), sql_TotalRecord, new ScalarHandler<Long>());
		QueryResult<SysServices> queryResult =new QueryResult<SysServices>(list, totalRecord);
		return queryResult;
		}
	public SysServices SysservicesSearchName(Integer id) throws Exception{
		String sql="select name from pmconf.sysservices where id=?";
		SysServices flag=null;
		QueryRunner runner =new QueryRunner();
		flag=runner.query(JdbcTools.getConnection(),sql, new BeanHandler<SysServices>(SysServices.class));
		return flag;
		}
	public void add(SysServices sys) throws Exception{
		String sql="insert into pmconf.sysservices values (?,?,?,?,?,?)";
		QueryRunner runner =new QueryRunner();
		Object[] params = {sys.getId(),sys.getName(),sys.getContent(),sys.getServersign(),sys.getTime_stamp(),sys.getType()};//初始化参数
		runner.update(JdbcTools.getConnection(),sql, params);
	}
    public void  del(Integer id) throws Exception{
    	String sql="delete from pmconf.sysservices where id=?";
		QueryRunner runner =new QueryRunner();
		runner.update(JdbcTools.getConnection(),sql, id);
    }
   public void  update(SysServices sys) throws Exception{
	   String sql="update pmconf.sysservices set name=? where id=?";
		QueryRunner runner =new QueryRunner();
		Object[] params = {sys.getName(),sys.getId()};
		runner.update(JdbcTools.getConnection(),sql, params);
      }
  }
