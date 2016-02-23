package cn.amichina.timecomm.quota.topupvolumn;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.util.GenerateTool;

@Service
public class TopUpVolumnService {

	@Resource
	private TopUpVolumnDao topUpVolumnDao;
	
	public List<TopUpVolumn> list(int pageNum, int pageSize) {
		return topUpVolumnDao.listAll(pageNum,pageSize);
	}

	public void delete(String policyId) {
		topUpVolumnDao.deleteById(policyId);
	}

	public void addNull() {
		List<String> policyIds = topUpVolumnDao.idList();
		String policyId = GenerateTool.idGenerator("TPVA", policyIds);
		topUpVolumnDao.addNull(policyId);
	}

	public String edit(String policyId, String policyName, long traffic) {
		return topUpVolumnDao.edit(policyId,policyName,traffic);
	}

	public Long getTotalNum() {
		return topUpVolumnDao.getTotalNum();
	}
	
	public TopUpVolumn getById(String id){
		return topUpVolumnDao.getById(id);
	}
}
