package cn.amichina.timecomm.quota.planbuilder;

import javax.annotation.Resource;

import cn.amichina.timecomm.quota.planbuilder.model.PlanBuilder;
import cn.amichina.timecomm.quota.planbuilder.service.PlanBuilderService;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;

import com.opensymphony.xwork2.ActionSupport;

public class PlanBuilderAction extends ActionSupport {

	@Resource
	private PlanBuilderService planBuilderService;
	private QueryInfo queryInfo;
	private String plannum;
	private PlanBuilder planBuilder;
	private PageBean<PlanBuilder> pageBean;
	@Override
	public String execute() throws Exception {
		pageBean=planBuilderService.pageQueryPlanBuilder(queryInfo);
		return SUCCESS;
	}
	public String page() throws Exception {
		return SUCCESS;
	}
	public String upate(){
		planBuilderService.update(planBuilder);
		return SUCCESS;
	}
	public String add(){
		planBuilderService.addBlankPlanBuilder();
		return SUCCESS;
	}
	public String del(){
		planBuilderService.remove(plannum);
		return SUCCESS;
	}
	public QueryInfo getQueryInfo() {
		return queryInfo;
	}
	public void setQueryInfo(QueryInfo queryInfo) {
		this.queryInfo = queryInfo;
	}
	public PlanBuilder getPlanBuilder() {
		return planBuilder;
	}
	public void setPlanBuilder(PlanBuilder planBuilder) {
		this.planBuilder = planBuilder;
	}
	public String getPlannum() {
		return plannum;
	}
	public void setPlannum(String plannum) {
		this.plannum = plannum;
	}
	public PageBean<PlanBuilder> getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean<PlanBuilder> pageBean) {
		this.pageBean = pageBean;
	}

}
