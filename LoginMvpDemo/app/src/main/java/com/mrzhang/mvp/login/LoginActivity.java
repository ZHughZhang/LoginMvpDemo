package com.mrzhang.mvp.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mrzhang.mvp.login.bean.LoginRespMsg;
import com.mrzhang.mvp.login.mvp.ILoginContract;
import com.mrzhang.mvp.login.mvp.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MrZhang on 2017/6/6.
 */

public class LoginActivity extends AppCompatActivity implements ILoginContract.LoginView {

	private final String TAG  = LoginActivity.class.getSimpleName();

	@BindView(R.id.accout)
	EditText mAccountEdText;
	@BindView(R.id.pas_word)
	EditText mPwdEdText;
	@BindView(R.id.sign_in)
	Button mSiginButton;

	private LoginPresenter mLoginPresenter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);
		mLoginPresenter = new LoginPresenter(this);

	}

	@OnClick(R.id.sign_in)
	public void onClick(View view){
		mLoginPresenter
				.login(mAccountEdText.getText().toString().trim()
						,mPwdEdText.getText().toString().trim());
	}

	@Override
	public void onError(String error) {
		Log.i(TAG,"error:"+error);
	}

	@Override
	public void onSuccess(LoginRespMsg loginRespMsg) {
		Log.i(TAG,loginRespMsg.toString());
		Toast.makeText(this,"登录成功："+ loginRespMsg.toString(), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void invaliNameOrPwd(String msg) {
		Toast.makeText(this,"。。。。"+msg,Toast.LENGTH_SHORT).show();
	}
}
