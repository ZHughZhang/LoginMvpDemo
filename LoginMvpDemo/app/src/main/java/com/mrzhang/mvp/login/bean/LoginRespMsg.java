package com.mrzhang.mvp.login.bean;

/**
 * Created by MrZhang on 2017/6/6.
 */

public class LoginRespMsg extends BaseRespMsg {
	private String token;
	private User data;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getData() {
		return data;
	}

	public void setData(User data) {
		this.data = data;
	}
}
