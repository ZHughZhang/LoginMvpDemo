package com.mrzhang.mvp.login.http;

import com.mrzhang.mvp.login.bean.LoginRespMsg;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by MrZhang on 2017/6/6.
 */

public interface RetrofitService {
	//首页列表
	/*@GET("campaign/recommend")
	Observable<>*/
	@FormUrlEncoded
	@POST("auth/login")
	Observable<LoginRespMsg> login (@Field("phone") String phone,@Field("password") String pwd);

	@FormUrlEncoded
	@POST("auth/login")
	Observable<String> loginString(@Field("phone")String phone,@Field("password") String pwd);
}
