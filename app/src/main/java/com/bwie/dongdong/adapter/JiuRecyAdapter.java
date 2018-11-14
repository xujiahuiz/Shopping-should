package com.bwie.dongdong.adapter;

import android.content.Context;

import com.bwie.dongdong.R;
import com.bwie.dongdong.bean.JiuBean;

import java.util.List;

public class JiuRecyAdapter extends RecycleAdapter<JiuBean.DataBean> {

    public JiuRecyAdapter(Context mcontext) {
        super(mcontext);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.layout_jiu_item;
    }

    @Override
    protected void convert(ViewHolder viewHolder, JiuBean.DataBean dataBean, int postion) {
        viewHolder.setImageUrl(R.id.jiu_pic, dataBean.getIcon()).setText(R.id.jiu_title, dataBean.getName());
    }
}
