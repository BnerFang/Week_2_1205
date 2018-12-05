package com.baway.week_2_1205.mvp.presenter;


import com.baway.week_2_1205.bean.LoginBean;
import com.baway.week_2_1205.mvp.MyCallBack;
import com.baway.week_2_1205.mvp.model.LoginModel;
import com.baway.week_2_1205.mvp.view.LoginView;

/**
 * 作者:  方诗康
 * 描述:
 */
public class LoginPresenter {

    private LoginView mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenter(LoginView loginView) {
        mLoginView = loginView;
        mLoginModel = new LoginModel();
    }

    //解绑
    public void datechView(){
        mLoginView = null;
    }

    public void login(String mobile,String password){
        mLoginModel.login(mobile, password, new MyCallBack() {
            @Override
            public void onSuccess(String result) {
                mLoginView.onLoginSuccess(result);
            }

            @Override
            public void onFailed(String error) {
                mLoginView.onLoginFailed(error);
            }
        });
    }


}
