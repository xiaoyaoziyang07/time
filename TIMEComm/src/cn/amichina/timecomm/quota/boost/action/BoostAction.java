package cn.amichina.timecomm.quota.boost.action;

import javax.annotation.Resource;

import cn.amichina.timecomm.quota.boost.entity.BoostInfo;
import cn.amichina.timecomm.quota.boost.service.BoostService;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BoostAction extends ActionSupport {
	@Resource
	private BoostService boostService;
	private BoostInfo boostInfo;
	private String boosttype;
	private PageBean<BoostInfo> pageBean;
	private QueryInfo queryInfo;
	private String prefix;
	private String policyid;
	@Override
	public String execute() throws Exception {
		pageBean = boostService.pageQueryBoostByBoosttype(queryInfo,boosttype);
		return SUCCESS;
	}

	public String update() {
		boostService.updateBoost(boostInfo);
		return SUCCESS;
	}

	public String delete() {
		boostService.delBoost(policyid);
		return SUCCESS;
	}

	public void setQueryInfo(QueryInfo queryInfo) {
		this.queryInfo = queryInfo;
	}
	public String add() {
		boostService.addBlankBoost(boosttype, prefix);
		return SUCCESS;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public PageBean<BoostInfo> getPageBean() {
		return pageBean;
	}
	public void setBoosttype(String boosttype) {
		this.boosttype = boosttype;
	}
	public void setBoostInfo(BoostInfo boostInfo) {
		this.boostInfo = boostInfo;
	}
	public void setPolicyid(String policyid) {
		this.policyid = policyid;
	}
	public QueryInfo getQueryInfo() {
		return queryInfo;
	}

	public BoostInfo getBoostInfo() {
		return boostInfo;
	}
	
}
