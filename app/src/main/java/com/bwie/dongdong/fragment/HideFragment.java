package com.bwie.dongdong.fragment;

import com.bwie.dongdong.fragmentp.HideFragmentPersenter;
import com.bwie.dongdong.mvp.persenter.BaseFragment;

public class HideFragment extends BaseFragment<HideFragmentPersenter> {


    @Override
    protected Class<HideFragmentPersenter> getLayout() {
        return HideFragmentPersenter.class;
    }


}
