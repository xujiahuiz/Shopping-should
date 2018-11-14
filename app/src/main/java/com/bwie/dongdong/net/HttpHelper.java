package com.bwie.dongdong.net;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpHelper {

    private static HttpHelper httpHelper;
    private Retrofit retrofit;

    private HttpHelper() {

    }

    public static HttpHelper getInstents() {
        if (httpHelper == null) {
            synchronized (HttpHelper.class) {
                httpHelper = new HttpHelper();
            }
        }
        return httpHelper;
    }

    //初始化
    public void init(String baseurl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //get方法
    public void doGet(String url, Map<String, String> map, Observer ob) {
        if (map == null) {
            map = new HashMap<>();
        }
        BaseService baseService = retrofit.create(BaseService.class);
        baseService.get(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ob);
    }

    public void doPost(String url, Map<String, String> map, Observer ob) {
        BaseService baseService = retrofit.create(BaseService.class);
        baseService.post(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ob);
    }
}
