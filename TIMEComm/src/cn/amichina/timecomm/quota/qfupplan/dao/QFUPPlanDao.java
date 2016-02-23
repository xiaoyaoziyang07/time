package cn.amichina.timecomm.quota.qfupplan.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.quota.qfupplan.entity.QFUPPlan;
import cn.amichina.timecomm.sys.model.QueryResult;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class QFUPPlanDao {

	public QueryResult<QFUPPlan> pageQueryQFUP(int startIndex, int pageSize){
		String sql ="SELECT * FROM pmconf.qfuppolicy ORDER BY createtime ASC limit ? offset ?";
		QueryRunner runner =new QueryRunner();
		try {
			List<QFUPPlan> qfupPlanoList = runner.query(JdbcTools.getConnection(), sql,new BeanListHandler<QFUPPlan>(QFUPPlan.class),pageSize,startIndex);
			String sql_TotalRecord="SELECT count(*) FROM pmconf.qfuppolicy";
			Long totalRecord =runner.query(JdbcTools.getConnection(), sql_TotalRecord, new ScalarHandler<Long>());
			return new QueryResult<QFUPPlan>(qfupPlanoList, totalRecord);
		} catch (SQLException e) {
			throw new DataAccessException("查询数据失败!", e);
		} 
	}
	public List<String> getIdAll() {
		String sql ="SELECT policyid FROM pmconf.qfuppolicy";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql,new ColumnListHandler<String>(1));
		} catch (SQLException e) {
			throw new DataAccessException("查询数据失败!", e);
		}
	}
	public QFUPPlan getByPolicyid(String policyid) {
		String sql ="SELECT * FROM pmconf.qfuppolicy WHERE policyid=?";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql,new BeanHandler<QFUPPlan>(QFUPPlan.class),policyid);
		} catch (SQLException e) {
			throw new DataAccessException("查询数据失败!", e);
		}
	}
	public void add(String policyid){
		String sql ="INSERT INTO pmconf.qfuppolicy(policyid) VALUES(?)";
		QueryRunner runner =new QueryRunner();
		try {
			runner.update(JdbcTools.getConnection(),sql,policyid);
		} catch (SQLException e) {
			throw new DataAccessException("添加数据时,发生异常!",e);
		}
	}
	public void del(String policyid){
		String sql ="DELETE FROM pmconf.qfuppolicy WHERE policyid =?";
		QueryRunner runner =new QueryRunner();
		try {
			runner.update(JdbcTools.getConnection(),sql,policyid);
		} catch (SQLException e) {
			throw new DataAccessException("删除数据时,发生异常!",e);
		}
	}
	public void update(QFUPPlan qfupPlan){
		String sql ="UPDATE  pmconf.qfuppolicy SET policyname=? ,quota=?,fup1_percent=?,fup1_ul=?,fup1_dl=?,fup2_percent=?,fup2_ul=?,fup2_dl=?,unit=? WHERE policyid=?";
		QueryRunner runner =new QueryRunner();
		try {
			runner.update(JdbcTools.getConnection(),sql,qfupPlan.getPolicyname(),qfupPlan.getQuota(),qfupPlan.getFup1_percent(),qfupPlan.getFup1_ul(),qfupPlan.getFup1_dl(),qfupPlan.getFup2_percent(),qfupPlan.getFup2_ul(),qfupPlan.getFup2_dl(),qfupPlan.getUnit(),qfupPlan.getPolicyid());
		} catch (SQLException e) {
			throw new DataAccessException("更新数据时,发生异常!",e);
		}
	}
}
