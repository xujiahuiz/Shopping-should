package com.bwie.dongdong.fragment;

import com.bwie.dongdong.fragmentp.HideFragmentPersenter;
import com.bwie.dongdong.fragmentp.ListFragmentPersenter;
import com.bwie.dongdong.mvp.persenter.BaseFragment;

public class ListFragment extends BaseFragment<ListFragmentPersenter> {
    @Override
    protected Class<ListFragmentPersenter> getLayout() {
        return ListFragmentPersenter.class;
    }
}
