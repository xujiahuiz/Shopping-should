package com.bwie.dongdong.acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.dongdong.R;
import com.bwie.dongdong.activityp.ShowPersenter;
import com.bwie.dongdong.mvp.persenter.BaseActivity;

public class ShowActivity extends BaseActivity<ShowPersenter> {


    @Override
    protected Class<ShowPersenter> getLayout() {
        return ShowPersenter.class;
    }
}
