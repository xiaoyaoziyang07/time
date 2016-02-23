package cn.amichina.timecomm.report.model;
/**
 * 
 * Create by 石磊  on 2015年8月20日 下午3:39:37
 *
 * 流量
 */
public class Traffic {
	/**
	 * 上传
	 */
	private Long upload;
	/**
	 * 下载
	 */
	private Long download;
	
	public Long getUpload() {
		return upload;
	}
	public void setUpload(Long upload) {
		this.upload = upload;
	}
	public Long getDownload() {
		return download;
	}
	public void setDownload(Long download) {
		this.download = download;
	}
	@Override
	public String toString() {
		return "Traffic [upload=" + upload + ", download=" + download + "]";
	}
	
	
}
