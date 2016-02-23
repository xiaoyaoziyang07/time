package cn.amichina.timecomm.quota.timebaseplan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class TimeBasePolicyDao {

	public List<Map<String, Object>> listAll(int pageNum, int pageSize) {
		
		String sql1 = "SELECT policyid, policyname, ruletype, createtime, starttime1, endtime1, starttime2, endtime2, upload1, download1, upload2, download2, isactived, createtime FROM pmconf.dayrule WHERE policyid IN ( SELECT policyid FROM pmconf.timepolicy_view ORDER BY createtime LIMIT ? OFFSET ? )";
		String sql2 = "SELECT policyid, policyname, ruletype, createtime, mon, tues, wed, thur, fri, sat, sun, upload, download, isactived, createtime from pmconf.weekrule WHERE policyid IN ( SELECT policyid FROM pmconf.timepolicy_view ORDER BY createtime LIMIT ? OFFSET ? )";
		String sql3 = "SELECT policyid, policyname, ruletype, createtime, starttime, endtime, upload, download, isactived, createtime from pmconf.periodrule WHERE policyid IN ( SELECT policyid FROM pmconf.timepolicy_view ORDER BY createtime LIMIT ? OFFSET ? )";
		
		try {
			QueryRunner runner = new QueryRunner();
			List<Map<String,Object>> list1 = runner.query(JdbcTools.getConnection(), sql1, new MapListHandler(),pageSize,pageSize*(pageNum-1));
			List<Map<String,Object>> list2 = runner.query(JdbcTools.getConnection(), sql2, new MapListHandler(),pageSize,pageSize*(pageNum-1));
			List<Map<String,Object>> list3 = runner.query(JdbcTools.getConnection(), sql3, new MapListHandler(),pageSize,pageSize*(pageNum-1));
			list1.addAll(list2);
			list1.addAll(list3);
			return list1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("查询总记录失败");
		}
	}

	public int addNull(String policyId) {
		try {
			String sql = "insert into pmconf.dayrule (policyid,policyname,ruletype,createtime) values (?,null,0,extract(epoch FROM now())*1000)";
			QueryRunner runner = new QueryRunner();
			return runner.update(JdbcTools.getConnection(), sql,policyId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("新建记录失败");
		}
	}

	public List<String> idList() {
		try {
			String sql = "select policyid from pmconf.timepolicy_view";
			QueryRunner runner = new QueryRunner();
			return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("查询idList失败");
		}
	}

	public Long getTotalNum() {
		try {
			String sql = "select count(*) from pmconf.timepolicy_view";
			QueryRunner runner = new QueryRunner();
			return runner.query(JdbcTools.getConnection(), sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("查询总记录数时失败");
		}
	}

	public int deleteByIdDay(String policyId) {
		try {
			String sql = "delete from pmconf.dayrule where policyid=?";
			QueryRunner runner = new QueryRunner();
			return runner.update(JdbcTools.getConnection(), sql, policyId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("删除天记录失败");
		}
	}

	public int deleteByIdweek(String policyId) {
		try {
			String sql = "delete from pmconf.weekrule where policyid=?";
			QueryRunner runner = new QueryRunner();
			return runner.update(JdbcTools.getConnection(), sql, policyId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("删除周数据失败");
		}
	}

	public int deleteByIdPeriod(String policyId) {
		try {
			String sql = "delete from pmconf.periodrule where policyid=?";
			QueryRunner runner = new QueryRunner();
			return runner.update(JdbcTools.getConnection(), sql, policyId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("删除指定时间数据失败");
		}
	}

	public Object[] findById(String policyId) {
		try {
			String sql = "select ruletype,createtime from pmconf.timepolicy_view where policyid=?";
			QueryRunner runner = new QueryRunner();
			return runner.query(JdbcTools.getConnection(), sql, new ArrayHandler(), policyId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("查询type失败");
		}
	}

	public void edit(Object[] type,String policyId, String policyName, int ruleType,
			String startTime1, String startTime2, String endTime1, String endTime2,
			long upload1, long upload2, long download1, long download2) {
		
		try {
			int t = Integer.parseInt(type[0].toString());
			QueryRunner runner = new QueryRunner();

			if(t!=ruleType){
				Connection conn = JdbcTools.getConnection();
				String sql1;
				if(t==0||t==1){
					sql1 = "delete from pmconf.dayrule where policyid=?";
				}else if(t==2){
					sql1 = "delete from pmconf.weekrule where policyid=?";
				}else {
					sql1 = "delete from pmconf.periodrule where policyid=?";
				}
				String sql2 = "insert into pmconf.dayrule (policyid,policyname,ruletype,starttime1,starttime2,endtime1,endtime2,upload1,upload2,download1,download2,createtime) values (?,?,?,?,?,?,?,?,?,?,?,?)";
				runner.update(conn, sql1, policyId);
				runner.update(conn,sql2,policyId,policyName,ruleType,startTime1,startTime2,endTime1,endTime2,upload1,upload2,download1,download2,type[1]);
			}else{
				String sql = "update pmconf.dayrule set policyname=?,ruletype=?,starttime1=?,starttime2=?,endtime1=?,endtime2=?,upload1=?,upload2=?,download1=?,download2=? where policyid=?";
				runner.update(JdbcTools.getConnection(),sql,policyName,ruleType,startTime1,startTime2,endTime1,endTime2,upload1,upload2,download1,download2,policyId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("编辑天记录失败");
		}
	}

	public void edit(Object[] type,String policyId, String policyName, int ruleType,
			int mon, int tues, int wed, int thur, int fri,
			int sat, int sun, long upload1, long download1) {
		int t = Integer.parseInt(type[0].toString());

		try {
			QueryRunner runner = new QueryRunner();

			if(t!=ruleType){
				Connection conn = JdbcTools.getConnection();
				String sql1;
				if(t==0||t==1){
					sql1 = "delete from pmconf.dayrule where policyid=?";
				}else if(t==2){
					sql1 = "delete from pmconf.weekrule where policyid=?";
				}else {
					sql1 = "delete from pmconf.periodrule where policyid=?";
				}
				String sql2 = "insert into pmconf.weekrule (policyid,policyname,ruletype,mon,tues,wed,thur,fri,sat,sun,upload,download,createtime) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
				runner.update(conn,sql2,policyId,policyName,ruleType,mon,tues,wed,thur,fri,sat,sun,upload1,download1,type[1]);
				runner.update(conn, sql1, policyId);
			}else{
				String sql = "update pmconf.weekrule set policyname=?,ruletype=?,mon=?,tues=?,wed=?,thur=?,fri=?,sat=?,sun=?,upload=?,download=? where policyid=?";
				runner.update(JdbcTools.getConnection(),sql,policyName,ruleType,mon,tues,wed,thur,fri,sat,sun,upload1,download1,policyId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("编辑周记录失败");
		}
	}

	public void edit(Object[] type,String policyId, String policyName, int ruleType,
			String startTime1, String endTime1, long upload1, long download1) {
		int t = Integer.parseInt(type[0].toString());
		
			try {
				QueryRunner runner = new QueryRunner();
				
				if(t!=ruleType){
					Connection conn = JdbcTools.getConnection();
					String sql1;
					if(t==0||t==1){
						sql1 = "delete from pmconf.dayrule where policyid=?";
					}else if(t==2){
						sql1 = "delete from pmconf.weekrule where policyid=?";
					}else {
						sql1 = "delete from pmconf.periodrule where policyid=?";
					}
					String sql2 = "insert into pmconf.periodrule (policyid,policyname,ruletype,starttime,endtime,upload,download,createtime) values (?,?,?,?,?,?,?,?)";
					runner.update(conn,sql2,policyId,policyName,ruleType,startTime1,endTime1,upload1,download1,type[1]);
					runner.update(conn, sql1, policyId);
				}else{
					String sql = "update pmconf.periodrule set policyname=?,ruletype=?,starttime=?,endtime=?,upload=?,download=? where policyid=?";
					runner.update(JdbcTools.getConnection(),sql,policyName,ruleType,startTime1,endTime1,upload1,download1,policyId);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DataAccessException("编辑指定时间记录失败");
			}
	}

	public TimeBasePolicy getById(String id, Object[] type) {
		
			QueryRunner runner = new QueryRunner();
			
			String sql1 = "SELECT policyid, policyname, ruletype, createtime, starttime1, endtime1, starttime2, endtime2, upload1, download1, upload2, download2, isactived, createtime FROM pmconf.dayrule WHERE policyid=?";
			String sql2 = "SELECT policyid, policyname, ruletype, createtime, mon, tues, wed, thur, fri, sat, sun, upload AS upload1, download AS download1, isactived, createtime from pmconf.weekrule WHERE policyid=?";
			String sql3 = "SELECT policyid, policyname, ruletype, createtime, starttime, endtime, upload AS upload1, download AS download1, isactived, createtime from pmconf.periodrule WHERE policyid=?";
			if(type.length==0){
				return null;
			}
			int t = Integer.parseInt(type[0].toString());
			if(t==0||t==1){
				try {
				return runner.query(JdbcTools.getConnection(), sql1, new BeanHandler<TimeBasePolicy>(TimeBasePolicy.class), id);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DataAccessException("查询单个记录失败");
				}
			}else if(t==2){
				try {
					return runner.query(JdbcTools.getConnection(), sql2, new BeanHandler<TimeBasePolicy>(TimeBasePolicy.class), id);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DataAccessException("查询单个记录失败");
				}
			}else{
				try {
					return runner.query(JdbcTools.getConnection(), sql3, new BeanHandler<TimeBasePolicy>(TimeBasePolicy.class), id);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DataAccessException("查询单个记录失败");
				}
			}
	}
}
