package com.bwie.dongdong.fragment;

import com.bwie.dongdong.fragmentp.HideFragmentPersenter;
import com.bwie.dongdong.fragmentp.ShopFragmentPersenter;
import com.bwie.dongdong.mvp.persenter.BaseFragment;

public class ShopFragment extends BaseFragment<ShopFragmentPersenter> {
    @Override
    protected Class<ShopFragmentPersenter> getLayout() {
        return ShopFragmentPersenter.class;
    }

    @Override
    public void onResume() {
        super.onResume();
        appIDetegate.notifyChange();
    }


}
