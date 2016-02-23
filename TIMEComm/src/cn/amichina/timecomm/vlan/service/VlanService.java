package cn.amichina.timecomm.vlan.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.timecomm.vlan.dao.VlanDao;
import cn.amichina.timecomm.vlan.model.Vlan;
/**
 * 
 * Create by 石磊  on 2015年10月12日 下午5:40:14
 * 
 *Vlan 业务组件
 */
@Service
public class VlanService {
	@Resource
	private VlanDao vlanDao;
	private static List<Vlan> vlanList=null;
	
	public List<Vlan> listVlan(){
		return getVlanList();
	}
	
	public List<Vlan> getVlanByvName(String vName){
		return vlanDao.getVlansByvName(vName);
	}
	
	public String getNameById(String vlanId) throws SQLException {
		return vlanDao.getVlanNameByvId(vlanId);
	}
	public List<Vlan> getVlanList() {
		synchronized(this){
			if(vlanList == null){
				vlanList=vlanDao.listVlan();
			}
		}
		return vlanList;
	}
}
