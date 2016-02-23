package cn.amichina.timecomm.model.application;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class ApplicationDao {

	public List<Application> listApp() throws SQLException {
		String sql = "SELECT id,name FROM pmconf.sysservices ORDER BY name";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(),sql, new BeanListHandler<Application>(Application.class));
	}

	public String getNameById(String appId) throws SQLException {
		String sql = "SELECT name FROM pmconf.sysservices where id=?";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ScalarHandler<String>(), appId);
	}

}
