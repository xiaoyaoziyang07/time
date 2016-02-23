package cn.amichina.timecomm.quota.planbuilder.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.quota.planbuilder.model.PlanBuilder;
import cn.amichina.timecomm.sys.model.QueryResult;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class PlanBuilderDao {

	public QueryResult<PlanBuilder> pageQueryPlanBuilder (Object pageSize, Object startIndex){
		String sql ="SELECT * FROM pmconf.plan_builder ORDER BY createtime ASC limit ? offset ?";
		QueryRunner runner =new QueryRunner();
		try {
			List<PlanBuilder> qfupPlanoList = runner.query(JdbcTools.getConnection(), sql,new BeanListHandler<PlanBuilder>(PlanBuilder.class),pageSize,startIndex);
			String sql_TotalRecord="SELECT count(*) FROM pmconf.plan_builder";
			Long totalRecord =runner.query(JdbcTools.getConnection(), sql_TotalRecord, new ScalarHandler<Long>());
			return new QueryResult<PlanBuilder>(qfupPlanoList, totalRecord);
		} catch (SQLException e) {
			throw new DataAccessException("查询数据失败!", e);
		} 
	}
	public void addBlankPlanBuilder(){
			String sql ="INSERT INTO pmconf.plan_builder(planid) VALUES(null)";
			QueryRunner runner =new QueryRunner();
			try {
				runner.update(JdbcTools.getConnection(),sql);
			} catch (SQLException e) {
				throw new DataAccessException("添加数据时,发生异常!",e);
			}
	}
	public List<String> getIdAll() {
		String sql ="SELECT planid FROM pmconf.plan_builder";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql,new ColumnListHandler<String>(1));
		} catch (SQLException e) {
			throw new DataAccessException("查询数据失败!", e);
		}
	}
	public void del(String planid){
		String sql ="DELETE FROM pmconf.plan_builder WHERE plannum =?";
		QueryRunner runner =new QueryRunner();
		try {
			runner.update(JdbcTools.getConnection(),sql,Long.parseLong(planid));
		} catch (SQLException e) {
			throw new DataAccessException("删除数据时,发生异常!",e);
		}
	}
	public void update(PlanBuilder planBuilder){
		String sql ="UPDATE pmconf.plan_builder  SET planname =?, paymenttype=?,plantype=?,source=?,packageid=?,timepolicyid=?,topuppolicyid=?,qfuppolicyid=?,freeboostid=?,paidboost=?,tierpolicyid=?,planid=? WHERE plannum=?";
		QueryRunner runner =new QueryRunner();
		try {
			runner.update(JdbcTools.getConnection(),sql,planBuilder.getPlanname(),planBuilder.getPaymenttype(),planBuilder.getPlantype(),planBuilder.getSource(),planBuilder.getPackageid(),planBuilder.getTimepolicyid(),planBuilder.getTopuppolicyid(),planBuilder.getQfuppolicyid(),planBuilder.getFreeboostid(),planBuilder.getPaidboost(),planBuilder.getTierpolicyid(),planBuilder.getPlanid(),planBuilder.getPlannum());
		} catch (SQLException e) {
			throw new DataAccessException("更新数据时,发生异常!",e);
		}
	}
}
