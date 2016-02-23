package cn.amichina.common.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.common.exception.BusinessException;
import cn.amichina.timecomm.sys.model.UserInfo;
import cn.amichina.timecomm.user.dao.UserDao;
import cn.amichina.timecomm.user.dao.UserInfoDao;
import cn.amichina.timecomm.user.exception.AccountAndPasswdMismatchException;
@Service
public class UserService {
	@Resource
	private UserInfoDao userInfoDao;
	@Resource
	private UserDao userDao;
	/**
	 * 处理登陆
	 * @param account
	 * @param pwd
	 * @return
	 * @throws UserAccountAndPasswdMismatchException
	 */
	public UserInfo login(String account,String pwd) throws AccountAndPasswdMismatchException{
			if(account==null||account.trim().isEmpty()||pwd==null||pwd.trim().isEmpty()){
				throw new AccountAndPasswdMismatchException("Your account name and password do not match. Please re-enter.");
			}
			UserInfo userInfo = userInfoDao.getUserInfoByAccountAndPwd(account, pwd);
			if(userInfo==null){
				throw new AccountAndPasswdMismatchException("Your account name and password do not match. Please re-enter.");
			}
			return userInfo;
	}
	/**
	 * 重置密码
	 * @param id
	 * @param oldPasswd
	 * @param newPasswd
	 * @throws ServiceException
	 */
	public void resetPasswordByUserIdAndOldPassword(String id,String oldPasswd,String newPasswd)throws BusinessException{
		try {
		UserInfo userInfo = userInfoDao.getUserInfoById(id);
		if(!userInfo.getPwd().trim().equals(oldPasswd)){
			throw new RuntimeException("ui.user.resetpasswd.error");
		}
		userInfo.setPwd(newPasswd);
		userInfoDao.updatePwd(userInfo);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	public void update(UserInfo userInfo) {
		userInfoDao.update(userInfo);
	}
	public UserInfo getUserInfoByUid(String id) throws BusinessException{
			return userInfoDao.getUserInfoById(id);
	}
	
	public List<String> userList(String userId){
		return userDao.userList(userId);
	}
}
