/*package cn.amichina.timecomm.servicesgroup.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.amichina.timecomm.group.model.ServiceGroup;
import cn.amichina.timecomm.servicesgroup.model.ServicesGroup;
import cn.amichina.timecomm.servicesgroup.service.ServicesGroupService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller
public class ServicesGroupAction extends ActionSupport {
	@Resource
	private ServicesGroupService serviceGroupService;
	private List<ServiceGroup> list;
	private Integer id;
	private ServicesGroup sys;
	
	
	@Override
	public String execute() throws Exception {
		System.out.println("ServicesGroupAction.execute()");
		return SUCCESS;
	}
	public String list() throws Exception {
		list =serviceGroupService.list();
		ActionContext.getContext().put("list", list);
		System.out.println(list);
		return SUCCESS;
	}
	
	public String add(){
		serviceGroupService.add(sys);
		return NONE;
	}
	public String del(){
		serviceGroupService.del(id);
		return NONE;
	}
	public String update(){
		serviceGroupService.update(sys);
		return NONE;
	}
	public ServicesGroup getModel() {
		if(sys==null){
			sys=new ServicesGroup();
		}
		return sys;
	}

}



*/