package com.bwie.dongdong.acitivity;

import com.bwie.dongdong.activityp.LoginPersenter;
import com.bwie.dongdong.mvp.persenter.BaseActivity;

public class LoginActivity extends BaseActivity<LoginPersenter> {


    @Override
    protected Class<LoginPersenter> getLayout() {
        return LoginPersenter.class;
    }
}
