package cn.amichina.timecomm.decision.idmapping.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.timecomm.decision.idmapping.MappingRecordBean;
import cn.amichina.timecomm.decision.idmapping.dao.IDMappingDao;

@Service
public class IDMappingService {

	@Resource
	private IDMappingDao mappingDao;
	
	public List<MappingRecordBean> toJson(String userId,String ip,String planId,int pageNum,int pageSize){
		return mappingDao.getRecord(userId,ip,planId,pageNum,pageSize);
	}

	public Long getTotalNum(String userId, String ip, String planId){
		return mappingDao.getTotalNum(userId,ip,planId);
	}
}
