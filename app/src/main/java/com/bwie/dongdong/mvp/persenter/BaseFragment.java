package com.bwie.dongdong.mvp.persenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.dongdong.mvp.view.AppIDetagate;

/**
 * 作者:徐家辉
 * 时间:2018/10/30
 * 作用:BaseFragment(fragment 基类)
 */
public abstract class BaseFragment<T extends AppIDetagate> extends Fragment {
    public T appIDetegate;

    public BaseFragment() {

        try {
            appIDetegate = getLayout().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

    }

    protected abstract Class<T> getLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        appIDetegate.Create(getLayoutInflater(), null, savedInstanceState);

        return appIDetegate.rootView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appIDetegate.inicontext(getContext());
        appIDetegate.initData();
    }

}
