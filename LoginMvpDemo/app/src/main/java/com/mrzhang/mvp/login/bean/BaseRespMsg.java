package com.mrzhang.mvp.login.bean;

import java.io.Serializable;

/**
 * Created by MrZhang on 2017/6/6.
 */

public class BaseRespMsg  implements Serializable{

	public final static int STATUS_SUCCES = 1;
	public final static int STATUS_ERROR =0;
	public final static String MSG_SUCCESS = "succes";

	protected  int status = STATUS_SUCCES;
	protected String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
