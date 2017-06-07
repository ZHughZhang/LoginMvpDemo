package com.mrzhang.mvp.login.mvp;

import android.text.TextUtils;
import android.util.Log;

import com.mrzhang.mvp.login.bean.LoginRespMsg;
import com.mrzhang.mvp.login.http.RetrofitManager;
import com.mrzhang.mvp.login.http.RetrofitService;
import com.mrzhang.mvp.login.utils.Constants;
import com.mrzhang.mvp.login.utils.DESUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MrZhang on 2017/6/6.
 */

public class LoginPresenter implements ILoginContract.LoginPresenter {


	private RetrofitService mRetrofitService;
	private ILoginContract.LoginView mLoginView;

	public LoginPresenter(ILoginContract.LoginView loginView) {
		mLoginView = loginView;
		mRetrofitService = RetrofitManager.getInatance().getServices(false);
	}

	@Override
	public void onCreater() {

	}

	@Override
	public void onStart() {

	}

	@Override
	public void onStop() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void login(String name, String pwd) {
		if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
			mLoginView.invaliNameOrPwd("用户和密码不能为空");
			return;
		}
		mRetrofitService.
				login(name, DESUtil.encode(Constants.DES_KEY,pwd))
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(new Observer<LoginRespMsg>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull LoginRespMsg loginRespMsg) {
							mLoginView.onSuccess(loginRespMsg);
					}

					@Override
					public void onError(@NonNull Throwable e) {
						Log.e("TAG","onError:"+e.getMessage());
						mLoginView.onError(e.getMessage());
					}

					@Override
					public void onComplete() {

					}
				});
	}
}
