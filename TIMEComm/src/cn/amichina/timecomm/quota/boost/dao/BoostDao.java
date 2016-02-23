package cn.amichina.timecomm.quota.boost.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.quota.boost.entity.BoostInfo;
import cn.amichina.timecomm.sys.model.QueryResult;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class BoostDao {

	public List<BoostInfo> getAllByBoosttype(String boosttype) {
		String sql ="SELECT * FROM pmconf.boostpolicy where boosttype = ?";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql,new BeanListHandler<BoostInfo>(BoostInfo.class),boosttype);
		} catch (SQLException e) {
			throw new DataAccessException("查询数据失败!", e);
		}
	}
	public BoostInfo getBypolicyid(String policyid) {
		String sql ="SELECT * FROM pmconf.boostpolicy where policyid = ?";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql,new BeanHandler<BoostInfo>(BoostInfo.class),policyid);
		} catch (SQLException e) {
			throw new DataAccessException("查询数据失败!", e);
		}
	}
	
	public void addBlankBoost(String id,String boosttype) {
		String sql ="INSERT INTO pmconf.boostpolicy (policyid,boosttype,createtime) VALUES(?,?,extract(epoch FROM now())*1000)";
		QueryRunner runner =new QueryRunner();
		try {
			runner.update(JdbcTools.getConnection(), sql,id,boosttype);
		} catch (SQLException e) {
			throw new DataAccessException("新增数据失败!", e);
		}
	}
	public List<String> getIdAll(String boosttype) {
		String sql ="SELECT policyid FROM pmconf.boostpolicy where boosttype =?";
		QueryRunner runner =new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql,new ColumnListHandler<String>(1),boosttype);
		} catch (SQLException e) {
			throw new DataAccessException("查询数据失败!", e);
		}
	}
	public void updateBoost(BoostInfo boostInfo) {
		String sql ="UPDATE pmconf.boostpolicy SET policyname=?,upload=?,download=?,duration=?,claimtime=?,validation=?,isactived=?,ismodify=?,content=? WHERE policyid =?";
		QueryRunner runner =new QueryRunner();
		try {
			 runner.update(JdbcTools.getConnection(), sql,boostInfo.getPolicyname(),boostInfo.getUpload(),boostInfo.getDownload(),boostInfo.getDuration(),boostInfo.getClaimtime(),boostInfo.getValidation(),boostInfo.getIsactived(),boostInfo.getIsmodify(),boostInfo.getContent(),boostInfo.getPolicyid());
		} catch (SQLException e) {
			throw new DataAccessException("更新数据失败!", e);
		}
	}
	public void delBoost(String id) {
		String sql ="DELETE FROM pmconf.boostpolicy WHERE  policyid= ?";
		QueryRunner runner =new QueryRunner();
		try {
			runner.update(JdbcTools.getConnection(), sql,id);
		} catch (SQLException e) {
			throw new DataAccessException("更新数据失败!", e);
		}
	}

	public QueryResult<BoostInfo> pageQueryBoostByBoosttype(Integer startIndex,
			Integer pageSize, String boosttype) {
		String sql ="SELECT * FROM pmconf.boostpolicy where boosttype = ? ORDER BY createtime DESC limit ? offset ?";
		QueryRunner runner =new QueryRunner();
		try {
			List<BoostInfo> boostInfoList = runner.query(JdbcTools.getConnection(), sql,new BeanListHandler<BoostInfo>(BoostInfo.class),boosttype,pageSize,startIndex);
			String sql_TotalRecord="SELECT count(*) FROM pmconf.boostpolicy where boosttype = ?";
			Long totalRecord =runner.query(JdbcTools.getConnection(), sql_TotalRecord, new ScalarHandler<Long>(),boosttype);
			return new QueryResult<BoostInfo>(boostInfoList, totalRecord);
		} catch (SQLException e) {
			throw new DataAccessException("查询数据失败!", e);
		} 
	}
}
