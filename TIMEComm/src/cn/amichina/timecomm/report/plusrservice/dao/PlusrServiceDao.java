package cn.amichina.timecomm.report.plusrservice.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.timecomm.report.plusrservice.model.PlusrService;
import cn.amichina.timecomm.sys.model.QueryResult;
import cn.amichina.timecomm.util.JdbcTools;
@Repository
public class PlusrServiceDao {
	/**
	 * 分页查询各个套餐报表
	 * @param startIndex
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @param provtypes
	 * @return
	 * @throws Exception 
	 */
	public  QueryResult<PlusrService> pageQueryPlusrServicesByStartDateEndAndDateAndProvtypes(int startIndex, int pageSize,Date startDate,Date endDate,String [] provtypes) throws Exception{
		StringBuffer sb_sql  =new StringBuffer();
		sb_sql.append("select * from (select splanid as server_name,count(*) as total  from pmlogic.plusrservice where provtype in (");
		StringBuffer sb_Provtype  =new StringBuffer();
		ayy2SQL(provtypes, sb_Provtype);
		sb_sql.append(sb_Provtype);
		sb_sql.append(") and createtime >= ? and createtime <=? and  splanid is not null and splanid !='' group by splanid  ) as foo order by total desc"); 	
		if(pageSize>-1){
			sb_sql.append(" limit ? ");
		}
		sb_sql.append(" offset ? ");
		String sql_TotalRecord="select count(*) from (select splanid as server_name,count(*) as total  from pmlogic.plusrservice where provtype in( "+sb_Provtype.toString()+")  and createtime >= ? and createtime <=? and  splanid is not null and splanid !=''   group by splanid  ) as foo ";
		QueryRunner runner =new QueryRunner();
			List<PlusrService> list =null;
			if(pageSize>-1){
				list =runner.query(JdbcTools.getConnection(), sb_sql.toString(), new BeanListHandler<PlusrService>(PlusrService.class),startDate.getTime(),endDate.getTime(),pageSize,startIndex);
			}else{
				list =runner.query(JdbcTools.getConnection(), sb_sql.toString(), new BeanListHandler<PlusrService>(PlusrService.class),startDate.getTime(),endDate.getTime(),startIndex);
			}
			 Long totalRecord =runner.query(JdbcTools.getConnection(), sql_TotalRecord, new ScalarHandler<Long>(),startDate.getTime(),endDate.getTime());
			 return new QueryResult<PlusrService>(list,totalRecord);
	}
	/**
	 * 将字符串数组转换为sql语句 
	 * @param provtypes
	 * @param sb_Provtype
	 */
	public void ayy2SQL(String[] provtypes, 
			StringBuffer sb_Provtype) {
		for (int i = 0; i < provtypes.length; i++) {
			sb_Provtype.append('\'');
			sb_Provtype.append(provtypes[i]);
			sb_Provtype.append('\'');
			if(i!=provtypes.length-1){
				sb_Provtype.append(",");
			}
		}
	}
	/**
	 * 获取套餐总数
	 * @param startDate
	 * @param endDate
	 * @param provtypes
	 * @return
	 * @throws Exception 
	 */
	public Long getPlusrServiceTotal(Date startDate,Date endDate,String [] provtypes) throws Exception{
		StringBuffer sb_Provtype  =new StringBuffer();
		ayy2SQL(provtypes,sb_Provtype);
		String sql="SELECT count(*) FROM pmlogic.plusrservice where createtime >= ? and createtime <=? and provtype in("+sb_Provtype.toString()+") and  splanid is not null and splanid !=''";
		QueryRunner runner =new QueryRunner();
			return runner.query(JdbcTools.getConnection(),sql, new ScalarHandler<Long>(),startDate.getTime(),endDate.getTime());
	}
	public static void main(String[] args) {
		StringBuffer sb_Provtype  =new StringBuffer();
		new PlusrServiceDao().ayy2SQL(new String[]{"t1","t2"}, sb_Provtype);
	}
}
