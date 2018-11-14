package com.bwie.dongdong.fragment;

import com.bwie.dongdong.entity.SpUtil;
import com.bwie.dongdong.fragmentp.HideFragmentPersenter;
import com.bwie.dongdong.fragmentp.MeFragmentPersenter;
import com.bwie.dongdong.mvp.persenter.BaseFragment;

public class MeFragment extends BaseFragment<MeFragmentPersenter> {
    @Override
    protected Class<MeFragmentPersenter> getLayout() {
        return MeFragmentPersenter.class;
    }

    @Override
    public void onResume() {
        super.onResume();
        Boolean isLogin = (Boolean) SpUtil.getInserter(getContext()).getSpData("isLogin", false);
        if (isLogin){
            String lname = (String) SpUtil.getInserter(getContext()).getSpData("lname", "");
            appIDetegate.setUserName(lname);
        }
    }
}
