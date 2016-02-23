package cn.amichina.timecomm.group.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.group.model.ServiceGroup;
import cn.amichina.timecomm.util.JdbcTools;
@Repository
public class ServiceGroupDao {
	/**
	 * 获取所有协议组
	 * @return
	 */
	public List<ServiceGroup> serviceGroupList() {
			QueryRunner runner = new QueryRunner();
			String sql = "SELECT * FROM pmconf.servicesgroup";
			try {
				return runner.query(JdbcTools.getConnection(), sql,
						new BeanListHandler<ServiceGroup>(ServiceGroup.class));
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
	}
}
