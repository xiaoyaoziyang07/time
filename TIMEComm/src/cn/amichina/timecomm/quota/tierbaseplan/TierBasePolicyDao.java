package cn.amichina.timecomm.quota.tierbaseplan;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class TierBasePolicyDao {

	public List<TierPolicy> listAll(int pageNum, int pageSize) {
		String sql ="select policyid,policyname,upload,download,isactived from pmconf.tierpolicy order by createtime asc limit ? offset ?";
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql, new BeanListHandler<TierPolicy>(TierPolicy.class),pageSize,pageSize*(pageNum-1));
		} catch (SQLException e) {
			throw new DataAccessException("查询TierBasePolicy数据列表失败");
		}
	}

	public void deleteById(String policyId) {
		String sql = "delete from pmconf.tierpolicy where policyid=?";
		QueryRunner runner = new QueryRunner();
		try {
			runner.update(JdbcTools.getConnection(), sql, policyId);
		} catch (SQLException e) {
			throw new DataAccessException("删除数据时出错");
		}
	}

	public void addNull(String policyId) {
		String sql = "insert into pmconf.tierpolicy (policyid,policyname,createtime) values (?,null,extract(epoch FROM now())*1000)";
		QueryRunner runner = new QueryRunner();
		try {
			runner.update(JdbcTools.getConnection(), sql,policyId);
		} catch (SQLException e) {
			throw new DataAccessException("插入数据失败");
		}
	}

	public String edit(String policyId, String policyName, long upload,long download) {
		String sql = "update pmconf.tierpolicy set policyname=?,upload=?,download=? where policyid=?";
		QueryRunner runner = new QueryRunner();
		try {
			int num = runner.update(JdbcTools.getConnection(), sql, policyName,upload,download,policyId);
			if(num==0){
				return "this policy has been deleted";
			}
			return "edit success";
		} catch (SQLException e) {
			throw new DataAccessException("更新数据失败");
		}
	}

	public List<String> idList() {
		try {
			String sql = "select policyid from pmconf.tierpolicy";
			QueryRunner runner = new QueryRunner();
			return runner.query(JdbcTools.getConnection(), sql, new ColumnListHandler<String>());
		} catch (SQLException e) {
			throw new DataAccessException("查询policyId时发生异常");
		}
	}

	public Long getTotalNum() {
		try {
			String sql = "select count(*) from pmconf.tierpolicy";
			QueryRunner runner = new QueryRunner();
			return runner.query(JdbcTools.getConnection(), sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			throw new DataAccessException("查询totalNum时发生异常");
		}
	}

	public TierPolicy getById(String id) {
			try {
				String sql = "select * from pmconf.tierpolicy where policyid=?";
				QueryRunner runner = new QueryRunner();
				return runner.query(JdbcTools.getConnection(), sql, new BeanHandler<TierPolicy>(TierPolicy.class),id);
			} catch (Exception e) {
				throw new DataAccessException("根据id查询时失败");
			}
	}
}
