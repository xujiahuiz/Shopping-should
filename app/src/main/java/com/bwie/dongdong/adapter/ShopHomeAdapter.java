package com.bwie.dongdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bwie.dongdong.R;
import com.bwie.dongdong.bean.ShopBean;

public class ShopHomeAdapter extends RecycleAdapter<ShopBean.DataBean> {
    private Context mcontext;
    private ShopAdapter shopAdapter;

    public ShopHomeAdapter(Context mcontext) {
        super(mcontext);
        this.mcontext = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_hide_stag;
    }

    @Override
    protected void convert(ViewHolder viewHolder, ShopBean.DataBean dataBean, int postion) {
        viewHolder.setText(R.id.hide_title, dataBean.getSellerName());
        RecyclerView view = (RecyclerView) viewHolder.getView(R.id.hide_shop);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        shopAdapter = new ShopAdapter(mcontext);
        view.setLayoutManager(staggeredGridLayoutManager);
        view.setAdapter(shopAdapter);
        shopAdapter.setList(dataBean.getList());
    }
}