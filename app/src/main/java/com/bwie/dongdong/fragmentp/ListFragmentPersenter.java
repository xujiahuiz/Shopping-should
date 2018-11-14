package com.bwie.dongdong.fragmentp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.bwie.dongdong.R;
import com.bwie.dongdong.adapter.RecyLeftAdapter;
import com.bwie.dongdong.adapter.ShopNameAdapter;
import com.bwie.dongdong.bean.JiuBean;
import com.bwie.dongdong.bean.RightBean;
import com.bwie.dongdong.mvp.view.AppIDetagate;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ListFragmentPersenter extends AppIDetagate {
    private ShopNameAdapter shopNameAdapter;
    private RecyLeftAdapter leftAdapter;

    @Override
    protected int getLayout() {
        return R.layout.layout_list_fragment;
    }

    private Context mcontext;
    private RecyclerView recyleft;
    private RecyclerView recyright;
    private int Cid = 1;
    private static final int LEFT_URL = 1;
    private static final int RIGHT_URL =2;


    @Override
    public void initData() {
        super.initData();
        initView();

        setLeftAdapter();
        setRightAdapter(1);

    }

    private void setRightAdapter(int cid) {
        Map<String, String> map = new HashMap<>();
        map.put("cid",cid+"");
        getString("/product/getProductCatagory",map,LEFT_URL);


    }

    private void setLeftAdapter() {
        getString("/product/getCatagory",null,RIGHT_URL);


    }

    @Override
    public void Success(int type, String data) {
        super.Success(type, data);
        switch (type){
            case LEFT_URL:
                Gson gson = new Gson();
                RightBean rightBean = gson.fromJson(data, RightBean.class);
                Log.d("Tagg",rightBean.getData().get(2).getName()+"");
                shopNameAdapter.setList(rightBean.getData());
                break;
            case RIGHT_URL:
                Gson gson1 = new Gson();
                final JiuBean leftBean = gson1.fromJson(data, JiuBean.class);
                leftAdapter.setList(leftBean.getData());
                break;
        }
    }

    @Override
    public void fila(String msg) {
        super.fila(msg);
    }

    private void initView() {
        recyleft = (RecyclerView) get(R.id.recycler_left);
        recyright = (RecyclerView) get(R.id.recycler_right);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mcontext);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        shopNameAdapter = new ShopNameAdapter(mcontext);
        recyright.setLayoutManager(linearLayoutManager1);
        recyright.setAdapter(shopNameAdapter);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyleft.setLayoutManager(staggeredGridLayoutManager);
        leftAdapter = new RecyLeftAdapter(mcontext);
        leftAdapter.setSetClickCid(new RecyLeftAdapter.SetClickCid() {
            @Override
            public void ClickOk(int cid) {
                setRightAdapter(cid);
            }
        });
        recyleft.setAdapter(leftAdapter);
    }

    @Override
    public void inicontext(Context context) {
        super.inicontext(context);
        this.mcontext = context;

    }
}
