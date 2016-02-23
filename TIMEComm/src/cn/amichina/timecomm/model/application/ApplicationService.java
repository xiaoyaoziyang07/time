package cn.amichina.timecomm.model.application;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
	
	@Resource
	ApplicationDao applicationDao;

	public List<Application> listApp() throws SQLException {
		return applicationDao.listApp();
	}

}
