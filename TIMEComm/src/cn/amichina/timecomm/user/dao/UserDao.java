package cn.amichina.timecomm.user.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class UserDao {

	public List<String> userList(String userId) {
		String sql = "select userid from pmlogic.plobject where userid like '" + userId + "%' order by userid limit 10";
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql,new ColumnListHandler<String>());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
