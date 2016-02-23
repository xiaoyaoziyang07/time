/*package cn.amichina.timecomm.sysservices.action;

import javax.annotation.Resource;

import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
import cn.amichina.timecomm.sysservices.model.SysServices;
import cn.amichina.timecomm.sysservices.service.SysServicesService;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class SysServicesAction extends ActionSupport {
	@Resource
	private SysServicesService sysServicesService;
	private Integer id;
	private SysServices sys;
	private QueryInfo queryInfo;
	private PageBean<SysServices> pageBean;
	
	public PageBean<SysServices> getPageBean() {
		return pageBean;
	}
	public void setQueryInfo(QueryInfo queryInfo) {
		this.queryInfo = queryInfo;
	}
	
	@Override
	public String execute() throws Exception {
		System.out.println("SysServicesAction.execute()");
		return SUCCESS;
	}
	public String list() throws Exception {
		pageBean =sysServicesService.listSysServices(queryInfo);
		ActionContext.getContext().put("pageBean", pageBean);
		return SUCCESS;
	}
	public String add(){
		sysServicesService.add(sys);
		return NONE;
	}
	public String update(){
		sysServicesService.update(sys);
		return NONE;
	}
	public String del(){
		sysServicesService.del(id);
		return NONE;
	}
}
*/