/*package cn.amichina.timecomm.sysservices.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
import cn.amichina.timecomm.sysservices.dao.SysServicesDao;
import cn.amichina.timecomm.sysservices.model.SysServices;

@Service
public class SysServicesService {
	
	private SysServicesDao sysServiceDao;

	@Resource
	public void setSysServiceDao(SysServicesDao sysServiceDao) {
		this.sysServiceDao = sysServiceDao;
	}

	//展示
	public PageBean<SysServices> listSysServices(QueryInfo queryInfo){
		PageBean<SysServices> pageBean =null;
		pageBean=new PageBean<SysServices>(queryInfo,sysServiceDao.listSysServices(queryInfo.getStartIndex(), queryInfo.getPageSize()));
			return pageBean;
	}
	
	//添加
	public void add(SysServices sys){
		//if(sys.getName().equals())
		sysServiceDao.add(sys);
	}
	//删除
	
	public void del(Integer id){
		sysServiceDao.del(id);
	}
	//修改
    public void update(SysServices sys){
    	sysServiceDao.update(sys);
    }
    
}*/