package com.bwie.dongdong.acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.dongdong.activityp.CompilePersenter;
import com.bwie.dongdong.mvp.persenter.BaseActivity;

public class CompileActivity extends BaseActivity<CompilePersenter> {


    @Override
    protected Class<CompilePersenter> getLayout() {
        return CompilePersenter.class;
    }
}
