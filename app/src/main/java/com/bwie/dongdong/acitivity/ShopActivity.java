package com.bwie.dongdong.acitivity;

import com.bwie.dongdong.activityp.ShopPersenter;
import com.bwie.dongdong.mvp.persenter.BaseActivity;

public class ShopActivity extends BaseActivity<ShopPersenter> {


    @Override
    protected Class<ShopPersenter> getLayout() {
        return ShopPersenter.class;
    }
}
