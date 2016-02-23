package cn.amichina.timecomm.servicesgroup.model;


public class ServicesGroup {
	private String groupid;
	private String groupname;
	private String content;
	private Integer createtime;
	private Integer type;
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "ServicesGroup [groupid=" + groupid + ", groupname=" + groupname
				+ ", content=" + content + ", createtime=" + createtime
				+ ", type=" + type + "]";
	}
	

}
