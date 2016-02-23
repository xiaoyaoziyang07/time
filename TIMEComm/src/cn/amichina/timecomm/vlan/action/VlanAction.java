package cn.amichina.timecomm.vlan.action;

import java.util.List;

import javax.annotation.Resource;

import cn.amichina.timecomm.vlan.model.Vlan;
import cn.amichina.timecomm.vlan.service.VlanService;

import com.opensymphony.xwork2.ActionSupport;

public class VlanAction extends ActionSupport {
	@Resource
	private VlanService vlanService;
	private List<Vlan> list ;
	private static final String JSONDATA = "toJson";
	private String name;
	@Override
	public String execute() throws Exception {
		if(name==null){
			name="";
		}
		list =vlanService.getVlanByvName(name);
		return JSONDATA;
	}
	public String listVlan() throws Exception {
		list = vlanService.listVlan();
		return JSONDATA;
	}
	public List<Vlan> getList() {
		return list;
	}
public void setName(String name) {
	this.name = name;
}
}
 