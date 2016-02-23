package cn.amichina.common.exception;

/**
 * 
 * Create by 石磊  on 2015年9月16日 上午11:29:32
 *
 * 自定义业务异常处理类 友好提示
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = 3152616724785436891L;

	public BusinessException(String frdMessage) {
		super(frdMessage);
	}

	public BusinessException(Throwable throwable) {
		super(throwable);
	}

	public BusinessException(Throwable throwable, String frdMessage) {
		super(throwable);
	}
}
