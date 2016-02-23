package cn.amichina.timecomm.sys.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.sys.model.RoleInfo;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class RoleInfoDao {
	/**
	 * 根据权限Id 获取权限信息
	 * 
	 * @param id
	 * @return
	 */
	public RoleInfo getRoleInfoById(Integer id)  {
		QueryRunner runner = new QueryRunner();
		String sql = "SELECT * FROM sys_admin.pre_common_rolevisual where id=?";
		try {
			return runner.query(JdbcTools.getConnection(), sql,
					new BeanHandler<RoleInfo>(RoleInfo.class), id);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * 获取所有权限信息
	 * 
	 * @return
	 */
	public List<RoleInfo> listRoleInfo() {
		QueryRunner runner = new QueryRunner();
		String sql = "SELECT * FROM sys_admin.pre_common_rolevisual";
		try {
			return runner.query(JdbcTools.getConnection(), sql,
					new BeanListHandler<RoleInfo>(RoleInfo.class));
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
}
