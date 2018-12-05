package com.baway.week_2_1205.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.baway.week_2_1205.MainActivity;
import com.baway.week_2_1205.R;
import com.baway.week_2_1205.fragment.QRCodeFragment;
import com.baway.week_2_1205.mvp.presenter.LoginPresenter;
import com.baway.week_2_1205.mvp.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    /**
     * 请输入手机号......
     */
    private EditText mEdMobile;
    /**
     * 请输入密码......
     */
    private EditText mEdPassword;
    /**
     * 记住密码
     */
    private CheckBox mCheckJz;
    /**
     * 自动登录
     */
    private CheckBox mCheckLogin;
    /**
     * 提交
     */
    private Button mBtnLogin;
    private LoginPresenter mLoginPresenter;
    private String mMobile;
    private String mPassword;
    private SharedPreferences mSP;
    private SharedPreferences.Editor mEdit;
    private boolean mISCHECK;
    private boolean mAUTO_ischeck;
    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        initView();
        mLoginPresenter = new LoginPresenter(this);
        mSP = getSharedPreferences("config", MODE_PRIVATE);
        mISCHECK = mSP.getBoolean("ISCHECK", false);
        mAUTO_ischeck = mSP.getBoolean("AUTO_ISCHECK", false);
        mEdit = mSP.edit();
        //判断记住密码多选框的状态
        if (mISCHECK) {
            //设置默认是记录密码状态
            mCheckJz.setChecked(true);
            mEdMobile.setText(mSP.getString("mobile", mMobile));
            mEdPassword.setText(mSP.getString("password", mPassword));
            //判断自动登陆多选框状态
            if (mAUTO_ischeck) {
                //设置默认是自动登录状态
                mCheckLogin.setChecked(true);
                //跳转界面
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
        mCheckLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //判断是否自动登录状态
                if (mCheckLogin.isChecked()) {
                    mEdit.putBoolean("AUTO_ISCHECK",true).commit();
                }else {
                    mEdit.putBoolean("AUTO_ISCHECK",false).commit();
                }
            }
        });

    }


    /**
     * 登录成功
     *
     * @param result
     */
    @Override
    public void onLoginSuccess(String result) {
        //记住密码
        if (mCheckJz.isChecked()){
            mEdit.putBoolean("ISCHECK",true);
            mEdit.putString("mobile",mMobile);
            mEdit.putString("password",mPassword);
            mEdit.commit();
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * 登录失败
     *
     * @param error
     */
    @Override
    public void onLoginFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    private void initView() {
        mEdMobile = (EditText) findViewById(R.id.ed_mobile);
        mEdPassword = (EditText) findViewById(R.id.ed_password);
        mCheckJz = (CheckBox) findViewById(R.id.check_jz);
        mCheckLogin = (CheckBox) findViewById(R.id.check_login);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_login:
                mMobile = mEdMobile.getText().toString().trim();
                mPassword = mEdPassword.getText().toString().trim();
                QRCodeFragment fragment = new QRCodeFragment();
                Bundle bundle = new Bundle();
                bundle.putString("mobile",mMobile);//这里的values就是我们要传的值
                fragment.setArguments(bundle);
                mLoginPresenter.login(mMobile, mPassword);
                break;
        }
    }


    /**
     * 防止内存泄露
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.datechView();
    }
}
