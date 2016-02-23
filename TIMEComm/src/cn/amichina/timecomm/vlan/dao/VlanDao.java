package cn.amichina.timecomm.vlan.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.util.JdbcTools;
import cn.amichina.timecomm.vlan.model.Vlan;

/**
 * 
 * Create by 石磊  on 2015年10月12日 下午5:25:31
 * Vlan 数据访问层
 *
 */
@Repository
public class VlanDao {
	/*
	 *SELECT SQL：SELECT id,name FROM pmconf.sysvlan 
	 */
	public List<Vlan> listVlan(){
		String sql ="SELECT id as vid,name as vname FROM pmconf.sysvlan  ORDER BY vname";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql,new BeanListHandler<Vlan>(Vlan.class));
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	/*
	 * SELECT SQL:SELECT id,name FROM pmconf.sysvlan where name like '%1%'
	 */
	public List<Vlan> getVlansByvName(String vName){
		String sql ="SELECT id as \"vId\",name as \"vName\" FROM pmconf.sysvlan where name like '%"+vName+"%'";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(),sql,new BeanListHandler<Vlan>(Vlan.class));
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	public String getVlanNameByvId(String vlanId) throws SQLException {
		String sql = "SELECT name FROM pmconf.sysvlan WHERE id=?";
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql, new ScalarHandler<String>(),vlanId);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
}
