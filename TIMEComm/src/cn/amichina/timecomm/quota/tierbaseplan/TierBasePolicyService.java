package cn.amichina.timecomm.quota.tierbaseplan;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.util.GenerateTool;

@Service
public class TierBasePolicyService {

	@Resource
	private TierBasePolicyDao tierPolicyDao;
	
	public List<TierPolicy> list(int pageNum, int pageSize) {
		return tierPolicyDao.listAll(pageNum,pageSize);
	}

	public void delete(String policyId) {
		tierPolicyDao.deleteById(policyId);
	}

	public void addNull() {
		List<String> policyIds = tierPolicyDao.idList();
		String policyId = GenerateTool.idGenerator("TrBP", policyIds);
		tierPolicyDao.addNull(policyId);
	}

	public String edit(String policyId, String policyName, long upload,long download) {
		return tierPolicyDao.edit(policyId,policyName,upload,download);
	}

	public Long getTotalNum() {
		return tierPolicyDao.getTotalNum();
	}
	
	public TierPolicy getById(String id) {
		return tierPolicyDao.getById(id);
	}
}
