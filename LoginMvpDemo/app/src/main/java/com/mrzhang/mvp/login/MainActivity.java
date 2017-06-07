package com.mrzhang.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrzhang.mvp.login.http.RetrofitManager;
import com.mrzhang.mvp.login.http.RetrofitService;
import com.mrzhang.mvp.login.utils.Constants;
import com.mrzhang.mvp.login.utils.DESUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();

	@BindView(R.id.accout)
	EditText mAccountText;
	@BindView(R.id.pas_word)
	EditText mPwdText;

	@BindView(R.id.sign_in)
	Button mSigin_inButton;

	@BindView(R.id.mvp_btn)
	Button mMvp_btn;

	//private Map<String,Object> mParm;

	private RetrofitService mRetrofit;
	private RetrofitService mRetrofitServiceJson;
	private RetrofitService mRetrofitServiceString;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		initRetrofit();
		initRetrofitSerice();
	}

	private void initRetrofitSerice(){
		mRetrofitServiceString = RetrofitManager.getInatance().getServices(true);
		mRetrofitServiceJson = RetrofitManager.getInatance().getServices(false);
	}
	private void initRetrofit(){
		mRetrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(Constants.API.BASE_URL)
				.build().create(RetrofitService.class);
	}
	@OnClick({R.id.sign_in,R.id.mvp_btn})
	public void onClick(View view){
		if (view.getId()==R.id.sign_in){
			login();
		}else if (view.getId() == R.id.mvp_btn){
			Intent intent = new Intent(MainActivity.this,LoginActivity.class);
			startActivity(intent);
		}

	}
	private void login(){
		String name = mAccountText.getText().toString().trim();
		String pwd = mPwdText.getText().toString().trim();
		Log.e(TAG,"name:"+name+", pwd:"+pwd);
		if (TextUtils.isEmpty(name)|| TextUtils.isEmpty(pwd)){
			Toast.makeText(this, "密码账号不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		mRetrofitServiceString
				.loginString(name, DESUtil.encode(Constants.DES_KEY,pwd))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<String>() {
					@Override
					public void onSubscribe(@NonNull Disposable d) {

					}

					@Override
					public void onNext(@NonNull String s) {
						Log.i(TAG,"onNext:"+s);
					}

					@Override
					public void onError(@NonNull Throwable e) {
						Log.i(TAG,"onError:"+e.getMessage());
					}

					@Override
					public void onComplete() {

					}
				});
	}
}
