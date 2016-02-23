package cn.amichina.timecomm.group.model;
/**
 * 
 * Create by 石磊  on 2015年8月20日 下午3:20:34
 *
 *	协议组
 */
public class ServiceGroup {
	/**
	 * 组Id
	 */
	private String groupId;
	/**
	 * 协议名
	 */
	private String groupname;
	/**
	 * 包含的协议ID
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Long createtime;
	/**
	 * 类型
	 */
	private Integer type;

	@Override
	public String toString() {
		return "ServiceGroup [groupId=" + groupId + ", groupname=" + groupname
				+ ", content=" + content + ", createtime=" + createtime
				+ ", type=" + type + "]";
	}
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
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

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
