package cn.amichina.timecomm.quota.boost.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.util.GenerateTool;
import cn.amichina.timecomm.quota.boost.dao.BoostDao;
import cn.amichina.timecomm.quota.boost.entity.BoostInfo;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
import cn.amichina.timecomm.sys.model.QueryResult;

@Service
public class BoostService {
	@Resource
	private BoostDao boostDao;

	public BoostInfo getBypolicyid(String policyid) {
		return boostDao.getBypolicyid(policyid);
	}

	public void addBlankBoost(String boosttype, String prefix) {
		String id = GenerateTool.idGenerator(prefix,
				boostDao.getIdAll(boosttype));
		boostDao.addBlankBoost(id, boosttype);
	}

	public void updateBoost(BoostInfo boostInfo) {
		boostDao.updateBoost(boostInfo);
	}

	public void delBoost(String id) {
		boostDao.delBoost(id);
	}

	public List<BoostInfo> getBoostByBoosttype(String boosttype) {
		return boostDao.getAllByBoosttype(boosttype);
	}

	public PageBean<BoostInfo> pageQueryBoostByBoosttype(QueryInfo queryInfo,
			String boosttype) {
		QueryResult<BoostInfo> queryResult = boostDao
				.pageQueryBoostByBoosttype(queryInfo.getStartIndex(),
						queryInfo.getPageSize(), boosttype);
		return new PageBean<BoostInfo>(queryInfo, queryResult);
	}
}
