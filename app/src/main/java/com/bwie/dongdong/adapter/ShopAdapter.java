package com.bwie.dongdong.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bwie.dongdong.R;
import com.bwie.dongdong.acitivity.ShopActivity;
import com.bwie.dongdong.bean.ShopBean;

public class ShopAdapter extends RecycleAdapter<ShopBean.DataBean.ListBean> {

    private Context context;

    public ShopAdapter(Context mcontext) {
        super(mcontext);
        this.context = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_hide_shop;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final ShopBean.DataBean.ListBean listBean, int postion) {
        String[] split = listBean.getImages().split("\\|");
        viewHolder.setImageUrl(R.id.hide_shop_pic, split[0]).setText(R.id.hide_shop_title, listBean.getTitle());
        viewHolder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("descurl", listBean.getDetailUrl());
                bundle.putInt("pid", listBean.getPid());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }
}
