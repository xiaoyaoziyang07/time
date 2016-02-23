package cn.amichina.timecomm.apistatus.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.amichina.timecomm.apistatus.model.Failured;
import cn.amichina.timecomm.apistatus.model.Notelog;
import cn.amichina.timecomm.apistatus.service.NotelogService;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class APIStatusAction extends ActionSupport {
	@Resource
	private NotelogService notelogService;
	private List<Failured> list;
	private String provtype ;
	private PageBean<Notelog> pageBean;
	private Date startDate;
	private Date endDate;
	private QueryInfo queryInfo;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public String provtype() throws Exception {
		this.pageBean =notelogService.pageQueryNotelogsByStartDateAndEndDateAndResultcodeNot0(queryInfo, startDate, endDate, provtype);
		return SUCCESS;
	}
	public String data() throws Exception {
		this.list =notelogService.getCountFailuredByStartDateAndEndDateAndResultcodeNot0(startDate, endDate);
		return SUCCESS;
	}
	public void setProvtype(String provtype) {
		this.provtype = provtype;
	}
	public List<Failured> getList() {
		return list;
	}
	public PageBean<Notelog> getPageBean() {
		return pageBean;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setQueryInfo(QueryInfo queryInfo) {
		this.queryInfo = queryInfo;
	}
}
