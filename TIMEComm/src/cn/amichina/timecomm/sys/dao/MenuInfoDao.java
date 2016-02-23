package cn.amichina.timecomm.sys.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.sys.model.MenuInfo;
import cn.amichina.timecomm.util.JdbcTools;
@Repository
public class MenuInfoDao {
	/**
	 * 获取所有导航信息
	 * @return
	 */
	public List<MenuInfo> listMenuInfo() {
			QueryRunner runner = new QueryRunner();
			String sql ="SELECT * FROM sys_admin.pre_common_nav ORDER BY sid,displayorder";
			try {
				return runner.query(JdbcTools.getConnection(),sql,new BeanListHandler<MenuInfo>(MenuInfo.class));
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}
	/**
	 * 根据sid 获取二级菜单
	 * @param sid
	 * @param sids
	 * @return
	 */
	public List<MenuInfo> getMenuInfosByPidFilterId(String sid,String[] sids)  {
			StringBuffer sb  =new StringBuffer();
			for (int i = 0; i < sids.length; i++) {
				sb.append('\'');
				sb.append(sids[i]);
				sb.append('\'');
				if(i!=sids.length-1){
					sb.append(",");
				}
			}
			QueryRunner runner = new QueryRunner();
			String sql ="SELECT * FROM sys_admin.pre_common_nav where pid=? and sid in("+sb.toString()+") and ntype!='1' ORDER BY sid,displayorder ";
			try {
				return runner.query(JdbcTools.getConnection(),sql,new BeanListHandler<MenuInfo>(MenuInfo.class),sid);
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}
}
