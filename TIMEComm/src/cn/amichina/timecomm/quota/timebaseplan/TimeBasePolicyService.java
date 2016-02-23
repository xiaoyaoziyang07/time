package cn.amichina.timecomm.quota.timebaseplan;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.exception.DataAccessException;
import cn.amichina.common.util.GenerateTool;

@Service
public class TimeBasePolicyService {

	@Resource
	private TimeBasePolicyDao timePolicyDao;
	
	public List<Map<String, Object>> list(int pageNum, int pageSize) {
		
		List<Map<String,Object>> list = timePolicyDao.listAll(pageNum,pageSize);
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				return Double.parseDouble(o1.get("createtime").toString())-Double.parseDouble(o2.get("createtime").toString())>0?1:-1;
			}
		});
		return list;
	}

	public String delete(String policyId) {
		Object[] ruletype = timePolicyDao.findById(policyId);
		if(ruletype.length==0){
			return "this policy has been deleted";
		}
		int t = Integer.parseInt(ruletype[0].toString());

		if(t==0||t==1){
			int num = timePolicyDao.deleteByIdDay(policyId);
			if(num==1){
				return "operation successfully completed";
			}else{
				return "this policy has been deleted";
			}
		}else if(t==2){
			int num = timePolicyDao.deleteByIdweek(policyId);
			if(num==1){
				return "operation successfully completed";
			}else{
				return "this policy has been deleted";
			}
		}else{
			int num = timePolicyDao.deleteByIdPeriod(policyId);
			if(num==1){
				return "operation successfully completed";
			}else{
				return "this policy has been deleted";
			}
		}
	}

	public String addNull() {
		try {
			List<String> policyIds = timePolicyDao.idList();
			String policyId = GenerateTool.idGenerator("TmBP", policyIds);
			timePolicyDao.addNull(policyId);
			return "Operation successfully completed";
		} catch (DataAccessException e) {
			return "Server is too busy";
		}
	}

	public Long getTotalNum() {
		return timePolicyDao.getTotalNum();
	}

	public void edit(String policyId, String policyName, int ruleType,
			String startTime1, String startTime2, String endTime1, String endTime2,
			long upload1, long upload2, long download1, long download2) {
		Object[] type = timePolicyDao.findById(policyId);
		if(type.length==0){
			return;
		}
		timePolicyDao.edit(type,policyId,policyName,ruleType,startTime1,startTime2,endTime1,endTime2,upload1,upload2,download1,download2);
	}

	public void edit(String policyId, String policyName, int ruleType,
			int mon, int tues, int wed, int thur, int fri,
			int sat, int sun, long upload1, long download1) {
		Object[] type = timePolicyDao.findById(policyId);
		if(type.length==0){
			return;
		}
		timePolicyDao.edit(type,policyId,policyName,ruleType,mon,tues,wed,thur,fri,sat,sun,upload1,download1);
	}

	public void edit(String policyId, String policyName, int ruleType,
			String startTime1, String endTime1, long upload1, long download1) {
		Object[] type = timePolicyDao.findById(policyId);
		if(type.length==0){
			return;
		}
		timePolicyDao.edit(type,policyId,policyName,ruleType,startTime1,endTime1,upload1,download1);
	}
	
	public TimeBasePolicy getById(String id) {
		Object[] type = timePolicyDao.findById(id);
		return timePolicyDao.getById(id,type);
	}
}
