package com.bwie.dongdong.adapter;

import android.content.Context;
import android.view.View;

import com.bwie.dongdong.R;
import com.bwie.dongdong.bean.JiuBean;

import java.util.List;


public class RecyLeftAdapter extends RecycleAdapter<JiuBean.DataBean> {
    private final Context context;

    public RecyLeftAdapter(Context mcontext) {
        super(mcontext);
        this.context = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fenlei_left_item;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final JiuBean.DataBean dataBean, int postion) {
        viewHolder.setImageUrl(R.id.fen_left_img, dataBean.getIcon()).setText(R.id.fen_left_txt, dataBean.getName());
        viewHolder.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cid = dataBean.getCid();
                setClickCid.ClickOk(cid);
            }
        });
    }

    private SetClickCid setClickCid;

    public interface SetClickCid {
        void ClickOk(int Cid);
    }

    public void setSetClickCid(SetClickCid setClickCid) {
        this.setClickCid = setClickCid;
    }
}
