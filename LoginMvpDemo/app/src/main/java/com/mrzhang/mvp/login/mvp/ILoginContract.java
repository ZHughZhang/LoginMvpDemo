package com.mrzhang.mvp.login.mvp;

import com.mrzhang.mvp.login.bean.LoginRespMsg;

/**
 * Created by MrZhang on 2017/6/6.
 */

public interface ILoginContract {
	//登录 activity/fragment

	interface LoginView extends IBaseView{
		void onSuccess(LoginRespMsg loginRespMsg);
		void invaliNameOrPwd(String msg);
	}
	//进行登录的请求 和数据处理
	interface LoginPresenter extends IBasePresenter{
		void login(String phone, String pwd);
	}
}
