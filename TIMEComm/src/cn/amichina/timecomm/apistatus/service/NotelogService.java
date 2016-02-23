package cn.amichina.timecomm.apistatus.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.timecomm.ResponseCode.ResponseCode;
import cn.amichina.timecomm.apistatus.dao.NotelogDao;
import cn.amichina.timecomm.apistatus.model.Failured;
import cn.amichina.timecomm.apistatus.model.Notelog;
import cn.amichina.timecomm.sys.model.PageBean;
import cn.amichina.timecomm.sys.model.QueryInfo;
import cn.amichina.timecomm.sys.model.QueryResult;
import cn.amichina.timecomm.util.DateUtil;
@Service
public class NotelogService {
	@Resource
	private NotelogDao notelogDao;
	/**
	 * 获取所有错误日志总数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Failured> getCountFailuredByStartDateAndEndDateAndResultcodeNot0(Date startDate,Date endDate) throws Exception{
		return notelogDao.getCountFailuredByStartDateAndEndDateAndResultcodeNot0(startDate, DateUtil.add(endDate, Calendar.DATE, 1));
	}
	/**
	 * 根据错误日志类型分页查询详细错误信息
	 * @param queryInfo
	 * @param startDate
	 * @param endDate
	 * @param provtype
	 * @return
	 */
	public PageBean<Notelog> pageQueryNotelogsByStartDateAndEndDateAndResultcodeNot0(QueryInfo queryInfo,Date startDate,Date endDate,String provtype) throws Exception{
		//获取所有错误日志类型
		QueryResult<Notelog> queryResult =notelogDao.pageQueryNotelogsByStartDateAndEndDateAndResultcodeNot0(queryInfo.getStartIndex(), queryInfo.getPageSize(), startDate, DateUtil.add(endDate, Calendar.DATE, 1),provtype);
		//给获取的错误日志添加序号
		for (int i = queryInfo.getPageSize(); i < queryInfo.getPageSize()+queryResult.getList().size(); i++) {
			Notelog notelog =queryResult.getList().get(i- queryInfo.getPageSize());
			notelog.setNum(queryInfo.getStartIndex()+i-queryInfo.getPageSize()+1);
			notelog.setReason(ResponseCode.get(notelog.getResultcode()));
		}
		return new PageBean<Notelog>(queryInfo,queryResult);
	}
}
