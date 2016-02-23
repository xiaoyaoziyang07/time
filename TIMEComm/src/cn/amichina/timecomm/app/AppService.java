package cn.amichina.timecomm.app;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class AppService {

	@Resource
	private AppDao appDao;
	
	public String getNameById(String appId) throws SQLException{
		return appDao.getNameById(appId);
	}
}
