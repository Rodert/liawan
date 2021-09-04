package com.liawan.model.enums;

/**
 * @author :
 * @createDate :
 */
public enum LiawanEnums {
	/**
	 * 运行错误
	 */
	ERROR(false, "系统错误"),
	/**
	 * 操作成功
	 */
	OPERATION_SUCCESS(true, "操作成功"),
	/**
	 * 操作错误
	 */
	OPERATION_ERROR(false, "操作失败"),
	/**
	 * 保存成功
	 */
	PRESERVE_SUCCESS(true, "保存成功"),
	/**
	 * 保存失败
	 */
	PRESERVE_ERROR(false, "保存失败");
	private boolean flag;
	private String message;

	private LiawanEnums(boolean flag, String message) {
		this.flag = flag;
		this.message = message;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
