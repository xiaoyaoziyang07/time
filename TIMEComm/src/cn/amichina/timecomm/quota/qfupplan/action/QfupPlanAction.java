package cn.amichina.timecomm.quota.qfupplan.action;

import javax.annotation.Resource;

import cn.amichina.timecomm.quota.qfupplan.entity.QFUPPlan;
import cn.amichina.timecomm.quota.qfupplan.service.QFUPPlanService;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;

import com.opensymphony.xwork2.ActionSupport;

public class QfupPlanAction extends ActionSupport {
	@Resource
	private QFUPPlanService qfupPlanService;
	private QueryInfo queryInfo;
	private PageBean<QFUPPlan> pageBean;
	private QFUPPlan qfupPlan;
	private String policyid;
	private String prefix;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public String pageQuery() throws Exception {
		pageBean =qfupPlanService.pageQueryQFUPPlan(queryInfo);
		return SUCCESS;
	}
	public String update() throws Exception {
		qfupPlanService.update(qfupPlan);
		return SUCCESS;
	}
	public String delete() throws Exception {
		qfupPlanService.delete(policyid);
		return SUCCESS;
	}
	public String addBlank() throws Exception {
		qfupPlanService.addBlank(prefix);
		return SUCCESS;
	}
public void setPolicyid(String policyid) {
	this.policyid = policyid;
}
	public void setQueryInfo(QueryInfo queryInfo) {
		this.queryInfo = queryInfo;
	}
	public QueryInfo getQueryInfo() {
		return queryInfo;
	}
	public PageBean<QFUPPlan> getPageBean() {
		return pageBean;
	}
	public QFUPPlan getQfupPlan() {
		return qfupPlan;
	}
	public void setQfupPlan(QFUPPlan qfupPlan) {
		this.qfupPlan = qfupPlan;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	
}
