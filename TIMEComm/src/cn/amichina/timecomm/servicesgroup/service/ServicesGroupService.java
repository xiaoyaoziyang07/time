/*package cn.amichina.timecomm.servicesgroup.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.timecomm.group.model.ServiceGroup;
import cn.amichina.timecomm.servicesgroup.dao.ServicesGroupDao;
import cn.amichina.timecomm.servicesgroup.model.ServicesGroup;

@Service
public class ServicesGroupService {
	
	private ServicesGroupDao servicesGroupDao;
    @Resource
	public void setServicesGroupDao(ServicesGroupDao servicesGroupDao) {
		this.servicesGroupDao = servicesGroupDao;
	}
	public List<ServiceGroup> list(){
		return servicesGroupDao.list();
	}
    public void add(ServicesGroup servicesGroup){
    	servicesGroupDao.add(servicesGroup);
    }
    public void del(Integer id){
    	servicesGroupDao.del(id);
    }
   public void update(ServicesGroup servicesGroup){
	   servicesGroupDao.update(servicesGroup);
   }
}
*/