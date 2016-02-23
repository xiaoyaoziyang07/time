package cn.amichina.timecomm.quota.planbuilder.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.util.GenerateTool;
import cn.amichina.timecomm.quota.boost.entity.BoostInfo;
import cn.amichina.timecomm.quota.boost.service.BoostService;
import cn.amichina.timecomm.quota.planbuilder.dao.PlanBuilderDao;
import cn.amichina.timecomm.quota.planbuilder.model.PlanBuilder;
import cn.amichina.timecomm.quota.qfupplan.entity.QFUPPlan;
import cn.amichina.timecomm.quota.qfupplan.service.QFUPPlanService;
import cn.amichina.timecomm.quota.tierbaseplan.TierBasePolicyService;
import cn.amichina.timecomm.quota.tierbaseplan.TierPolicy;
import cn.amichina.timecomm.quota.timebaseplan.TimeBasePolicy;
import cn.amichina.timecomm.quota.timebaseplan.TimeBasePolicyService;
import cn.amichina.timecomm.quota.topupvolumn.TopUpVolumn;
import cn.amichina.timecomm.quota.topupvolumn.TopUpVolumnService;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
import cn.amichina.timecomm.sys.model.QueryResult;

@Service
public class PlanBuilderService {
	@Resource
	private PlanBuilderDao planBuilderDao;
	@Resource
	private QFUPPlanService qfupPlanService;
	@Resource
	private TierBasePolicyService tierBasePolicyService;
	@Resource
	private TimeBasePolicyService timeBasePolicyService;
	@Resource
	private BoostService boostService;
	@Resource
	private TopUpVolumnService topUpVolumnService;
	
	public PageBean<PlanBuilder> pageQueryPlanBuilder(QueryInfo queryInfo){
		QueryResult<PlanBuilder> queryResult=planBuilderDao.pageQueryPlanBuilder(queryInfo.getPageSize(), queryInfo.getStartIndex());
		for (PlanBuilder planBuilder : queryResult.getList()) {
			if(planBuilder==null||planBuilder.getPlantype()==null)continue;
			if(planBuilder.getPlantype()==1){
				String policyid =planBuilder.getTierpolicyid();
				TierPolicy tierPolicy =tierBasePolicyService.getById(policyid);
				if(tierPolicy!=null){
					planBuilder.setTierbaseDL(tierPolicy.getDownload());
					planBuilder.setTierbaseUL(tierPolicy.getUpload());
					tierPolicy=null;
				}
				//
				policyid=planBuilder.getTimepolicyid();
				TimeBasePolicy timeBase =timeBasePolicyService.getById(policyid);
				if(timeBase!=null){
					long  ul =0l;
					long  dl =0l;
				if(timeBase.getRuleType()==0){
				}else if(timeBase.getRuleType()==1){
					dl=timeBase.getDownload1();
					ul=timeBase.getUpload1();
				}else if(timeBase.getRuleType()==2){
					dl=timeBase.getDownload2();
					ul=timeBase.getUpload2();
				}else if(timeBase.getRuleType()==3){
					dl=timeBase.getDownload1();
					ul=timeBase.getUpload1();
				}
				planBuilder.setTimebaseDL(dl);
				planBuilder.setTimebaseUL(ul);
				planBuilder.setRuleType(timeBase.getRuleType());
				timeBase=null;
				policyid=planBuilder.getQfuppolicyid();
				}
				QFUPPlan qfup =qfupPlanService.getByPolicyid(policyid);
				if(qfup!=null){
				planBuilder.setQfupQuote(qfup.getQuota());
				qfup=null;
				}
				
			}else if(planBuilder.getPlantype()==2){
				String policyid =planBuilder.getFreeboostid();
				
				BoostInfo boostInfo =boostService.getBypolicyid(policyid);
				if(boostInfo != null){
					planBuilder.setBoostDL(boostInfo.getDownload());
					planBuilder.setBoostUL(boostInfo.getUpload());
					boostInfo=null;
				}
			}else if(planBuilder.getPlantype()==3){
				String policyid =planBuilder.getPaidboost();
				BoostInfo boostInfo =boostService.getBypolicyid(policyid);
				if(boostInfo!=null){
					planBuilder.setBoostDL(boostInfo.getDownload());
					planBuilder.setBoostUL(boostInfo.getUpload());
					boostInfo=null;
				}
			}else if(planBuilder.getPlantype()==4){
				String policyid =planBuilder.getTopuppolicyid();
				TopUpVolumn topup= topUpVolumnService.getById(policyid);
				if(topup!=null){
					planBuilder.setTopupTraffic(topup.getTraffic());
					topup=null;
				}
			}else if(planBuilder.getPlantype()==5){
				String policyid=planBuilder.getTimepolicyid();
				TimeBasePolicy timeBase =timeBasePolicyService.getById(policyid);
				if(timeBase!=null){
					long  ul =0l;
					long  dl =0l;
				if(timeBase.getRuleType()==0){
				}else if(timeBase.getRuleType()==1){
					dl=timeBase.getDownload1();
					ul=timeBase.getUpload1();
				}else if(timeBase.getRuleType()==2){
					dl=timeBase.getDownload2();
					ul=timeBase.getUpload2();
				}else if(timeBase.getRuleType()==3){
					dl=timeBase.getDownload1();
					ul=timeBase.getUpload1();
				}
				planBuilder.setTimebaseDL(dl);
				planBuilder.setTimebaseUL(ul);
				planBuilder.setRuleType(timeBase.getRuleType());
				timeBase=null;
				}
			}
		}
		return new PageBean<PlanBuilder>(queryInfo, queryResult);
	}
	public void addBlankPlanBuilder(){
		planBuilderDao.addBlankPlanBuilder();
	}
	public void update(PlanBuilder planBuilder){
		planBuilderDao.update(planBuilder);
	}
	public void remove(String planid){
		planBuilderDao.del(planid);
	}
}
