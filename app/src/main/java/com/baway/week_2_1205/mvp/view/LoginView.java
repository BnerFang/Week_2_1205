package com.baway.week_2_1205.mvp.view;

/**
 * 作者:  方诗康
 * 描述:
 */
public interface LoginView {

    void onLoginSuccess(String result);//成功
    void onLoginFailed(String error);//失败

}
