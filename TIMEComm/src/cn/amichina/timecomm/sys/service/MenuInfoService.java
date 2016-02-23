package cn.amichina.timecomm.sys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.amichina.timecomm.sys.dao.MenuInfoDao;
import cn.amichina.timecomm.sys.dao.RoleInfoDao;
import cn.amichina.timecomm.sys.model.MenuInfo;
import cn.amichina.timecomm.sys.model.RoleInfo;
import cn.amichina.timecomm.user.dao.UserInfoDao;
/**
 * 
 * Create by 石磊  on 2015年7月30日 上午10:15:03
 *
 */
@Service
public class MenuInfoService {
	@Resource
	private MenuInfoDao mDao = new MenuInfoDao();
	@Resource
	private UserInfoDao uDao =new UserInfoDao();
	@Resource
	private RoleInfoDao rDao =new RoleInfoDao();
	public List<MenuInfo> getMenuListByUid(String uid) {
		 String str_groupright =uDao.getUserInfoById(uid).getGroupList();
		 String user_grouprights [] = str_groupright.split("\\|");
		 StringBuffer sb_groupright = new StringBuffer();
		for (String rId : user_grouprights) {
			RoleInfo roleInfo =rDao.getRoleInfoById(Integer.parseInt(rId));
			//开始获取权限id
			if (null != roleInfo){
				sb_groupright.append(roleInfo.getGroupright());
				sb_groupright.append(",");
            }
		}
		String[] strArr_User_Grouprights =sb_groupright.toString().split(",");
		List<MenuInfo>  list_User_MenuInfos = mDao.getMenuInfosByPidFilterId("0", strArr_User_Grouprights);
		for (MenuInfo menuInfo : list_User_MenuInfos) {
			hasSubMenuInfos(menuInfo,strArr_User_Grouprights);
		}
		return list_User_MenuInfos;
	}
	private void hasSubMenuInfos(MenuInfo menuInfo,String[] filtersIds) {
		
		List<MenuInfo>  subMenuInfos =mDao.getMenuInfosByPidFilterId(menuInfo.getSid(), filtersIds);
		if(subMenuInfos==null||subMenuInfos.isEmpty()){
			return;
		}
		menuInfo.setSubMenus(subMenuInfos);
		for (MenuInfo tmp_menuInfo : subMenuInfos) {
			hasSubMenuInfos(tmp_menuInfo,filtersIds);
		}
	}
	/**
	 * 获取菜单列表
	 * 
	 */
	public List<MenuInfo> getMenuList()  {
		List<MenuInfo> list = mDao.listMenuInfo();
		List<MenuInfo> result = new ArrayList<MenuInfo>();
		for (MenuInfo menuInfo : list) {
			// 如果不是菜单 终止本次循环
			if (!"0".equals(menuInfo.getNtype())
					&& !"-1".equals(menuInfo.getNtype())) {
				continue;
			}
			int level = menuInfo.getSid().length() / 3;
			// 如果是一级菜单,直接插入
			if (1 == level) {
				result.add(menuInfo);
				continue;
			}
			MenuInfo parent = getParent(menuInfo, result);
			if (null == parent) {
				continue;
			}
			List<MenuInfo> subMenus = parent.getSubMenus();
			if (null == subMenus) {
				subMenus = new ArrayList<MenuInfo>();
				parent.setSubMenus(subMenus);
			}
			subMenus.add(menuInfo);
		}
		return result;
	}
	
	private  MenuInfo getParent(MenuInfo menuInfo, List<MenuInfo> all) {
		if (null == all) {
			return null;
		}
		for (MenuInfo parent : all) {
			String sid = parent.getSid();
			String pid = menuInfo.getPid();
			if (sid.equals(pid)) { 
				return parent;
			} else if (pid.startsWith(sid)) {
				return getParent(menuInfo, parent.getSubMenus());
			}
		}
		return null;
	}
	@Deprecated
    public void createSubmenu(MenuInfo parent, StringBuffer menuStr, String groupList){
        boolean ishasChild = null != parent.getSubMenus() && !parent.getSubMenus().isEmpty();
        menuStr.append("<li sid='" + parent.getSid() + "' >");
        String dropdownIcon = "<i class='icon-double-angle-right' ></i> ";
        if ("0".equals(parent.getNtype())){
            dropdownIcon = "<i class='icon-"+parent.getSid()+"' ></i>";
        }
        if (ishasChild){
            menuStr.append("<a href='#' title='" + parent.getTitle() + "' class='dropdown-toggle maintitle'  url='" + parent.getUrl() + "'  > " + dropdownIcon);
            menuStr.append(" <span class='menu-text'>").append(parent.getTitle()).append("</span> <b class='arrow icon-angle-down'></b> </a>");
        }else{
            menuStr.append("<a href='#' title='" + parent.getTitle() + "' url='" + parent.getUrl() + "' class='leaf' > <i class='icon-double-angle-right'></i> " + parent.getTitle() + "</a> ");
        }
        if (null != parent.getSubMenus() && !parent.getSubMenus().isEmpty()){
            menuStr.append("<ul class='submenu'>");
            for (MenuInfo bean : parent.getSubMenus()){
                if (!groupList.contains("," + bean.getSid() + ",")){
                    continue;
                }
                createSubmenu(bean, menuStr, groupList);
            }
            menuStr.append("</ul>");
        }
        menuStr.append("</li>");
    }
}
