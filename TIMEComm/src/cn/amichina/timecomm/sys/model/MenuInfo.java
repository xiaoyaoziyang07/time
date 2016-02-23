package cn.amichina.timecomm.sys.model;

import java.util.List;
/**
 * 
 * Create by 石磊 on 2015年7月28日 下午10:00:17
 * 菜单实体
 */
public class MenuInfo
{
	public MenuInfo() {}
	/**
     * 菜单ID
     */
    private String id;
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	/**
     * 菜单有序ID
     */
    private String sid;
    
    /**
     * 父菜单ID
     */
    private String pid;
    
    /**
     * 菜单名
     */
    private String title;
    
    /**
     * 子菜单
     */
    private List<MenuInfo> subMenus;
    
    /**
     * 显示顺序
     */
    private int displayorder;

	/**
     * 关联 URL
     */
    private String url;
    
    /**
     * TODO 啥意思-1
     * 节点类型   -1  主菜单 1 按钮
     */
    private String ntype;
    
	@Override
	public String toString() {
		return "MenuInfo [sid=" + sid + ", pid=" + pid + ", title=" + title
				+ ", subMenus=" + subMenus + ", displayorder=" + displayorder
				+ ", url=" + url + ", ntype=" + ntype + "]";
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNtype() {
		return ntype;
	}

	public void setNtype(String ntype) {
		this.ntype = ntype;
	}
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public List<MenuInfo> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<MenuInfo> subMenus) {
		this.subMenus = subMenus;
	}

	public int getDisplayorder() {
		return displayorder;
	}

	public void setDisplayorder(int displayorder) {
		this.displayorder = displayorder;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    
}
