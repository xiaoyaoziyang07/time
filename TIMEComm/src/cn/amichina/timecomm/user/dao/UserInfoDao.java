package cn.amichina.timecomm.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.sys.model.UserInfo;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class UserInfoDao {
	/**
	 * 根据用户名，密码获取账号信息
	 * @param account
	 * @param pwd
	 * @return 用户信息，失败返回null
	 * @throws SQLException 
	 */
	public UserInfo getUserInfoByAccountAndPwd(String account,String pwd) {
			QueryRunner runner = new QueryRunner();
			String sql = "SELECT * FROM sys_admin.pre_common_member WHERE account=? and pwd =?";
			try {
				return runner.query(JdbcTools.getConnection(), sql,
						new BeanHandler<UserInfo>(UserInfo.class), account,pwd);
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}

	public UserInfo getUserInfoById(String id)  {
			QueryRunner runner = new QueryRunner();
			String sql = "SELECT * FROM sys_admin.pre_common_member WHERE id=?";
			try {
				return runner.query(JdbcTools.getConnection(), sql,
						new BeanHandler<UserInfo>(UserInfo.class), id);
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}
	public void update(UserInfo userInfo) {
			QueryRunner runner = new QueryRunner();
			String sql = "UPDATE sys_admin.pre_common_member SET account=?,pwd=?,username=?,email=?,unit=?,phone=?,grouplist=?,department=?,time_stamp=?,blocid=?  WHERE id=?";
			try {
				runner.update(JdbcTools.getConnection(), sql, userInfo.getAccount(),userInfo.getPwd(),userInfo.getUsername(),userInfo.getEmail(),userInfo.getUnit(),userInfo.getPhone(),userInfo.getGroupList(),userInfo.getDepartment(),userInfo.getTime_stamp(),userInfo.getBlocid(),userInfo.getId());
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}
	public void updatePwd(UserInfo userInfo) {
		String sql = "UPDATE sys_admin.pre_common_member set pwd=? where account=? ";
		QueryRunner runner = new QueryRunner();
		try {
			runner.update(JdbcTools.getConnection(), sql,userInfo.getPwd(),userInfo.getAccount());
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
}
