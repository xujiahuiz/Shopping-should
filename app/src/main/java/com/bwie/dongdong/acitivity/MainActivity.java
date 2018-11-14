package com.bwie.dongdong.acitivity;

import com.bwie.dongdong.activityp.MainPersenter;
import com.bwie.dongdong.mvp.persenter.BaseActivity;
/**
 * 作者:徐家辉
 * 时间:2018/10/30
 * 作用:MainActivity(欢迎页)
 */
public class MainActivity extends BaseActivity<MainPersenter> {

    @Override
    protected Class<MainPersenter> getLayout() {
        return MainPersenter.class;
    }
}

