package cn.amichina.timecomm.quota.qfupplan.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.util.GenerateTool;
import cn.amichina.timecomm.quota.qfupplan.dao.QFUPPlanDao;
import cn.amichina.timecomm.quota.qfupplan.entity.QFUPPlan;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
import cn.amichina.timecomm.sys.model.QueryResult;

@Service
public class QFUPPlanService {
	@Resource
	private QFUPPlanDao qfupPlanDao;

	public QFUPPlan getByPolicyid(String policyid) {
		return qfupPlanDao.getByPolicyid(policyid);
	}
	public PageBean<QFUPPlan> pageQueryQFUPPlan(QueryInfo queryInfo){
		QueryResult<QFUPPlan> queryResult =qfupPlanDao.pageQueryQFUP(queryInfo.getStartIndex(), queryInfo.getPageSize());
		return new PageBean<QFUPPlan>(queryInfo,queryResult); 
	}
	public void update(QFUPPlan qfupPlan){
		qfupPlanDao.update(qfupPlan);
	}
	public void delete(String policyid){
		qfupPlanDao.del(policyid);
	}
	public void addBlank(String prefix){
		String id =GenerateTool.idGenerator(prefix, qfupPlanDao.getIdAll());
		qfupPlanDao.add(id);
	}
}
