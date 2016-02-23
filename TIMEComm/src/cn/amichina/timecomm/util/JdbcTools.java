package cn.amichina.timecomm.util;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
@Repository
public class JdbcTools {
	private static DataSource dataSource;
	private static final ThreadLocal<Connection> local =new ThreadLocal<Connection>();
	public static DataSource getDataSource() {
		return dataSource;
	}
	@Resource
	public void setDataSource(DataSource ds) {
		JdbcTools.dataSource = ds;
	}
	public static Connection getConnection() {
		try {
			if(local.get()==null){
				local.set(dataSource.getConnection());
			}
			return local.get();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	public static void closeConnection() {
		try {
			if(local.get()!=null){
				local.get().close();
				local.remove();
			}
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
}
