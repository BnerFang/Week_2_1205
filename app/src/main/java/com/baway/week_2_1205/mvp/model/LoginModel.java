package com.baway.week_2_1205.mvp.model;


import com.baway.week_2_1205.bean.LoginBean;
import com.baway.week_2_1205.mvp.MyCallBack;
import com.baway.week_2_1205.util.OkHttpUtil;
import com.google.gson.Gson;

/**
 * 作者:  方诗康
 * 描述:
 */
public class LoginModel  {


    public void login(String mobile, String password, final MyCallBack callBack){
        final String loginPath = "http://www.xieast.com/api/user/login.php?username="+mobile+"&password="+password;

        new OkHttpUtil().OkHttpGet(loginPath).setOkHttpListener(new OkHttpUtil.OkHttpListener() {
            @Override
            public void OkHttpSuccess(String data) {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(data, LoginBean.class);
                if (loginBean.getCode() == 100){
                    callBack.onSuccess(loginBean.getMsg());
                }else {
                    callBack.onFailed(loginBean.getMsg());
                }
            }

            @Override
            public void OkHttpError(String error) {

            }
        });



    }

}
