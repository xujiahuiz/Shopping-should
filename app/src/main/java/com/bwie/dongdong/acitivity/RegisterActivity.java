package com.bwie.dongdong.acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.dongdong.activityp.RegisterPersenter;
import com.bwie.dongdong.mvp.persenter.BaseActivity;

public class RegisterActivity extends BaseActivity<RegisterPersenter> {


    @Override
    protected Class<RegisterPersenter> getLayout() {
        return RegisterPersenter.class;
    }
}
