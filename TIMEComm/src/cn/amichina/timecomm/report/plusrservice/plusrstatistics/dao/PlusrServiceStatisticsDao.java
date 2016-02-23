package cn.amichina.timecomm.report.plusrservice.plusrstatistics.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.timecomm.report.plusrservice.plusrstatistics.model.PlusrServiceStatistics;
import cn.amichina.timecomm.util.DateUtil;
import cn.amichina.timecomm.util.JdbcTools;

@Repository
public class PlusrServiceStatisticsDao {
	public PlusrServiceStatistics getPlusrServiceStatisticesByStar1(Date stat1) {
		String sql = "SELECT stat1,stat2,stat3,stat4,stat5,stat6,stat7,stat8,stat9,stat10,stat11,stat12 FROM pmstaticrep.t_stat_1 WHERE stat1 =?";
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql,new BeanHandler<PlusrServiceStatistics>(PlusrServiceStatistics.class), Long.parseLong(DateUtil.toStr(stat1,DateUtil.EN_DATA_FORMAT_YYYYMMDD)));
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	public List<PlusrServiceStatistics> getPlusrServiceStatisticesHourDataByStar1(Date stat1) {
		String sql = "SELECT stat1,stat2,stat3,stat4,stat5,stat6,stat7,stat8,stat9,stat10,stat11,stat12  FROM pmstaticrep.t_stat_3 WHERE stat1 >= ? AND stat1 <=? ORDER by stat1";
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(JdbcTools.getConnection(), sql,new BeanListHandler<PlusrServiceStatistics>(PlusrServiceStatistics.class), Long.parseLong(DateUtil.toDBStr(stat1) + "000000"),Long.parseLong(DateUtil.toDBStr(stat1) + "240000"));
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
}
