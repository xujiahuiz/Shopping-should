package com.bwie.dongdong.mvp.persenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bwie.dongdong.mvp.view.AppIDetagate;

/**
 * 作者:徐家辉
 * 时间:2018/10/30
 * 作用:BaseActivity(activity 基类)
 */
public abstract class BaseActivity<T extends AppIDetagate> extends AppCompatActivity {
    private T appIDetegate;

    public BaseActivity() {
        try {
            appIDetegate = getLayout().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    protected abstract Class<T> getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appIDetegate.Create(getLayoutInflater(), null, savedInstanceState);
        setContentView(appIDetegate.rootView());
        appIDetegate.inicontext(this);
        appIDetegate.initData();
    }
}
