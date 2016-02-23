package cn.amichina.timecomm.app;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class AppDao {

	public String getNameById(String appId) throws SQLException {
		String sql = "SELECT name FROM pmconf.sysservices WHERE id=?";
		QueryRunner runner = new QueryRunner();
		return runner.query(JdbcTools.getConnection(), sql, new ScalarHandler<String>(), appId);
	}

}
