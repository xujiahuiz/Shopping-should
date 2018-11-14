package com.bwie.dongdong.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bwie.dongdong.R;
import com.bwie.dongdong.bean.ShopBean;
import com.bwie.dongdong.view.ShoCarView;

import java.util.List;

public class ShopCarItemAdapter extends RecycleAdapter<ShopBean.DataBean.ListBean> {
    private Context context;

    public ShopCarItemAdapter(Context mcontext, List<ShopBean.DataBean.ListBean> list) {
        super(mcontext);
        this.context = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_xlistview_item;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final ShopBean.DataBean.ListBean listBean, final int postion) {
        Log.d("Taggggg", listBean.getTitle() + ">>>>>>>");
        ShoCarView mview = (ShoCarView) viewHolder.getView(R.id.shop_view);
        mview.setListener(new ShoCarView.DataBackListener() {
            @Override
            public void dataBack(int data) {
                listBean.setNum(data);
                notifyItemChanged(postion);
                comedataListener.comeData(listBean.getNum());
            }

            @Override
            public void selectBack(boolean ischeck) {
                listBean.setChecked(ischeck);
                notifyItemChanged(postion);
                comedataListener.comeData(listBean.getNum());
            }
        });
        String[] split = listBean.getImages().split("\\|");
        Log.d("Tag",listBean.getNum()+">>>>");
        mview.setNum(listBean.getNum());
        mview.setShopImageUrl(split[0]);
        mview.setShopTitle(listBean.getTitle());
        mview.setShopPrice(listBean.getPrice() + "");
        mview.setIscheck(listBean.isChecked());

    }

    //自定义接口，回调数据
    public interface comeDataListener {
        void comeData(int data);
    }

    private comeDataListener comedataListener;

    public void setComedataListener(comeDataListener comedataListener) {
        this.comedataListener = comedataListener;
    }

    public void toase(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
