package com.bwie.dongdong.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bwie.dongdong.R;
import com.bwie.dongdong.bean.ShopBean;

import java.util.List;

public class ShopCarAdapter extends CommonAdapter<ShopBean.DataBean> {

    private Context context;
    private ShopCarItemAdapter.comeDataListener listener;

    public void setListener(ShopCarItemAdapter.comeDataListener listener) {
        this.listener = listener;
    }

    public ShopCarAdapter(Context mContext, List<ShopBean.DataBean> datas) {
        super(mContext, datas);
        this.context = mContext;
    }

    @Override
    public void convert(IViewHolder holer, ShopBean.DataBean dataBean) {
        holer.setText(R.id.menchant_title, dataBean.getSellerName());
        holer.setImageResource(R.id.menchant_pic,R.drawable.store);
        RecyclerView recycle = (RecyclerView) holer.getView(R.id.menchant_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(linearLayoutManager);
        Log.d("Tag111", dataBean.getList().size() + ">>>>");
        ShopCarItemAdapter shopCarItemAdapter = new ShopCarItemAdapter(context, dataBean.getList());
        shopCarItemAdapter.setComedataListener(listener);
        recycle.setAdapter(shopCarItemAdapter);
        shopCarItemAdapter.setList(dataBean.getList());
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_xlistview;
    }
}
