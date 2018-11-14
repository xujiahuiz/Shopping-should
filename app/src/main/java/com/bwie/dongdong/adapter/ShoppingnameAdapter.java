package com.bwie.dongdong.adapter;

import android.content.Context;
import android.widget.EditText;

import com.bwie.dongdong.R;
import com.bwie.dongdong.bean.RightBean;

import java.util.List;


public class ShoppingnameAdapter extends RecycleAdapter<RightBean.DataBean.ListBean> {
    private Context context;
    private EditText num;

    public ShoppingnameAdapter(Context mcontext) {
        super(mcontext);
        this.context = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.right_child_item;
    }

    @Override
    protected void convert(final ViewHolder viewHolder, final RightBean.DataBean.ListBean listBean, final int postion) {

        viewHolder.setText(R.id.fen_right_child_txt, listBean.getName()).setImageUrl(R.id.fen_right_child_img, listBean.getIcon());

    }

}

