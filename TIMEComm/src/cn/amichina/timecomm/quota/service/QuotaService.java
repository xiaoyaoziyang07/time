package cn.amichina.timecomm.quota.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.timecomm.quota.dao.QuotaDao;
import cn.amichina.timecomm.quota.model.Quota;

@Service
public class QuotaService {
	@Resource
	private QuotaDao quota;
	/**
	 * 根据用户Id查询套餐信息
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public Quota getQuotaByUserId(String userId) throws Exception {
		return quota.getQuotaByUserId(userId);
	}
}
