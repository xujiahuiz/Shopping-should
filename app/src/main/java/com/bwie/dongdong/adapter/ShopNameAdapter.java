package com.bwie.dongdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bwie.dongdong.R;
import com.bwie.dongdong.bean.RightBean;

import java.util.List;

public class ShopNameAdapter extends RecycleAdapter<RightBean.DataBean> {
    private Context context;

    public ShopNameAdapter(Context mcontext) {
        super(mcontext);
        this.context = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fenlei_right;
    }

    @Override
    protected void convert(ViewHolder viewHolder, RightBean.DataBean dataBean, int postion) {
        viewHolder.setText(R.id.fen_right_title, dataBean.getName());
        RecyclerView recyShop = (RecyclerView) viewHolder.getView(R.id.fen_right_child);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyShop.setLayoutManager(staggeredGridLayoutManager);
        ShoppingnameAdapter shoppingAdapter = new ShoppingnameAdapter(context);
        recyShop.setAdapter(shoppingAdapter);
        shoppingAdapter.setList(dataBean.getList());
    }
}
