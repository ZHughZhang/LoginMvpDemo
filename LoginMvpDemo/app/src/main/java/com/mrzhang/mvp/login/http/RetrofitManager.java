package com.mrzhang.mvp.login.http;

import com.mrzhang.mvp.login.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by MrZhang on 2017/6/6.
 */

public class RetrofitManager {
	private static RetrofitManager mInatance;
	private RetrofitService mService;
	private RetrofitService mRetrofitService;

	private RetrofitManager() {
	}

	public static RetrofitManager getInatance() {
		if (mInatance == null) {
			synchronized (RetrofitManager.class) {
				if (mInatance == null) {
					mInatance = new RetrofitManager();
				}
			}
		}
		return mInatance;
	}

	public RetrofitService getServices(boolean justString) {
		if (justString && mService == null) {
			mService = new Retrofit.Builder()
					.baseUrl(Constants.API.BASE_URL)
					.addConverterFactory(ScalarsConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.build().create(RetrofitService.class);
			return mService;
		} else if (mRetrofitService == null) {
			mRetrofitService = new Retrofit.Builder()
					.baseUrl(Constants.API.BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.build().create(RetrofitService.class);
			return mRetrofitService;
		}
		return (justString ? mService : mRetrofitService);
	}
}
