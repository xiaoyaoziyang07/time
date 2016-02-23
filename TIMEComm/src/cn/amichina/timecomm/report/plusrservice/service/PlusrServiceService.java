package cn.amichina.timecomm.report.plusrservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.timecomm.report.plusrservice.dao.PlusrServiceDao;
import cn.amichina.timecomm.report.plusrservice.model.PlusrService;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
import cn.amichina.timecomm.sys.model.QueryResult;

@Service
public class PlusrServiceService {
	@Resource
	private PlusrServiceDao plusrServiceDao;
	private QueryInfo queryInfo;
	public PageBean<PlusrService>  getPlusrServiceByProvtypes(QueryInfo queryInfo,Date startDate ,Date endDate,String [] provtypes) throws Exception{
		QueryResult<PlusrService> queryResult =plusrServiceDao.pageQueryPlusrServicesByStartDateEndAndDateAndProvtypes(queryInfo.getStartIndex(), -1, startDate,endDate, provtypes);
		PageBean<PlusrService> pageBean =new PageBean<PlusrService>(queryInfo,queryResult);
		Long plusrServiceTotal = plusrServiceDao.getPlusrServiceTotal(startDate,endDate,provtypes);
		for (PlusrService plusrService : pageBean.getList()) {
			Double ratio =plusrService.getTotal().doubleValue() / plusrServiceTotal.doubleValue() * 100;
			plusrService.setRatio(ratio);
		}
		List<PlusrService> ohterList = new ArrayList<PlusrService>();
		if (pageBean.getList().size() > queryInfo.getPageSize()) {
			ohterList= pageBean.getList().subList(queryInfo.getPageSize(),pageBean.getList().size());
			pageBean.setList(new Vector(pageBean.getList().subList(0,queryInfo.getPageSize())));
		}
		Long totalSum=0l;
		for (int i =0 ; i<ohterList.size();i++) {
			if(ohterList.get(i).getTotal()!=null){
				totalSum +=ohterList.get(i).getTotal();
			}
		}
		PlusrService plusrService =new PlusrService();
		plusrService.setServer_name("Other");
		plusrService.setTotal(totalSum);
		Double ratio =totalSum.doubleValue()/ plusrServiceTotal.doubleValue()* 100;
		plusrService.setRatio(ratio);
		pageBean.getList().add(plusrService);
		return pageBean;
	}
	public PageBean<PlusrService> pageQueryPlusrServiceGrossAdd(Date startDate ,Date endDate,QueryInfo queryInfo) throws Exception{
		return getPlusrServiceByProvtypes(queryInfo,startDate,endDate,new String[]{"T1004","T1005","T1006","T1001"});
	}
	public PageBean<PlusrService> pageQueryPlusrServiceSuspend(Date startDate ,Date endDate,QueryInfo queryInfo) throws Exception{
		return getPlusrServiceByProvtypes(queryInfo,startDate,endDate,new String[]{"T1004"});
	}
	public PageBean<PlusrService> pageQueryPlusrServiceTerminate(Date startDate ,Date endDate,QueryInfo queryInfo) throws Exception{
		return getPlusrServiceByProvtypes(queryInfo,startDate,endDate,new String[]{"T1006"});
	}
	public PageBean<PlusrService> pageQueryPlusrServiceNewAddition(Date startDate ,Date endDate,QueryInfo queryInfo) throws Exception{
		return getPlusrServiceByProvtypes(queryInfo,startDate,endDate,new String[]{"T1001"});
	}
	public PageBean<PlusrService> pageQueryPlusrServiceNetGross(Date startDate ,Date endDate,QueryInfo queryInfo) throws Exception{
		return getPlusrServiceByProvtypes(queryInfo,startDate,endDate,new String[]{"T1001","T1005"});
	}
	public PageBean<PlusrService> pageQueryPlusrServiceRestoration(Date startDate ,Date endDate,QueryInfo queryInfo) throws Exception{
		return getPlusrServiceByProvtypes(queryInfo,startDate,endDate,new String[]{"T1005"});
	}
	public  PageBean<PlusrService> pageQueryPlusrServiceByPlusrServiceName(String stautsType ,QueryInfo queryInfo,Date startDate, Date endDate) throws Exception{
		if(stautsType==null){
			throw new RuntimeException("套餐名不能为null！");
		}
		if(stautsType.equals("grossAdd")){
			return pageQueryPlusrServiceGrossAdd(startDate,endDate,queryInfo);
		}else if(stautsType.equals("suspend")){
			return pageQueryPlusrServiceSuspend(startDate,endDate,queryInfo);
		}else if(stautsType.equals("terminate")){
			return pageQueryPlusrServiceTerminate(startDate,endDate,queryInfo);
		}else if(stautsType.equals("newAddition")){
			return pageQueryPlusrServiceNewAddition(startDate,endDate,queryInfo);
		}else if(stautsType.equals("netGross")){
			return pageQueryPlusrServiceNetGross(startDate,endDate,queryInfo);
		}else if(stautsType.equals("restoration")){
			return pageQueryPlusrServiceRestoration(startDate,endDate,queryInfo);
		}
		throw new RuntimeException("套餐名:"+stautsType+" 不存在！");
	}
} 
