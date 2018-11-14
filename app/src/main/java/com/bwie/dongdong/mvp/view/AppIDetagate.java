package com.bwie.dongdong.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.dongdong.net.BaseObserver;
import com.bwie.dongdong.net.HttpHelper;

import java.io.IOException;
import java.util.Map;


import okhttp3.ResponseBody;

public abstract class AppIDetagate implements IDetegate {

    private View rootView;
    private Context mcontext;
    private SparseArray<View> views = new SparseArray<>();

    @Override
    public void initData() {

    }

    @Override
    public void Create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = inflater.inflate(getLayout(), null, false);
    }

    protected abstract int getLayout();

    @Override
    public View rootView() {
        return rootView;
    }

    @Override
    public void inicontext(Context context) {
        this.mcontext = context;
    }

    public <T extends View> T get(int id) {
        T view = (T) views.get(id);
        if (view == null) {
            view = rootView.findViewById(id);
            views.put(id, view);
        }
        return view;
    }

    public void setClick(View.OnClickListener click, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            rootView.findViewById(id).setOnClickListener(click);
        }
    }

    //getSting方法
    @Override
    public void getString(String url, Map<String, String> map, final int type) {
        BaseObserver<ResponseBody> ob = new BaseObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    Success(type, string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                String message = e.getMessage();
                fila(message);
            }
        };
        HttpHelper.getInstents().doGet(url, map, ob);
    }

    //postSting方法
    @Override
    public void postString(String url, Map<String, String> map, final int type) {
        BaseObserver<ResponseBody> ob = new BaseObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    Success(type, string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                String message = e.getMessage();
                fila( message);

            }
        };
        HttpHelper.getInstents().doPost(url, map, ob);

    }

    // 成功
    public void Success(int type, String data) {
    }

    //失败
    public void fila( String msg) {

    }


}
