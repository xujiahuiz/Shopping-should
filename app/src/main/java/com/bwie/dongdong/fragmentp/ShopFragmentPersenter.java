package com.bwie.dongdong.fragmentp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.dongdong.R;
import com.bwie.dongdong.adapter.CommonAdapter;
import com.bwie.dongdong.adapter.ShopCarAdapter;
import com.bwie.dongdong.adapter.ShopCarItemAdapter;
import com.bwie.dongdong.bean.ShopBean;
import com.bwie.dongdong.entity.SpUtil;
import com.bwie.dongdong.mvp.view.AppIDetagate;
import com.example.xlistviewlib.XListView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopFragmentPersenter extends AppIDetagate implements ShopCarItemAdapter.comeDataListener {
    private Context mcontext;
    private XListView xlistview;
    private ImageView imgck;
    private TextView tprice;
    private TextView tnum;
    private static final int MERCHANT_URL = 1;
    private ShopBean shopBean;
    private int numAll = 0;
    private boolean flag = true;
    private double priceAll = 0.0;
    private ShopCarAdapter shopCarAdapter;

    @Override
    protected int getLayout() {
        return R.layout.layout_shop_fragment;
    }

    @Override
    public void initData() {
        super.initData();
        initView();
        notifyChange();
        /*boolean isLogin = (boolean) SpUtil.getInserter(mcontext).getSpData("isLogin", true);
        if (!isLogin) {
            Toast.makeText(mcontext, "请先登陆", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> map = new HashMap<>();
        int uid = (int) SpUtil.getInserter(mcontext).getSpData("uid", 0);
        map.put("uid", uid + "");
        Log.d("ShopFragmentPersenter", uid + "???");
        getString("/product/getCarts", map, MERCHANT_URL);*/
        setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.img_ck:
                        isCkAll();
                        break;
                }
            }
        }, R.id.img_ck);
    }

    //设置全选全不选
    private void isCkAll() {
        if (flag) {
            imgck.setImageResource(R.drawable.ck_yes);
            for (int i = 0; i < shopBean.getData().size(); i++) {
                for (int j = 0; j < shopBean.getData().get(i).getList().size(); j++) {
                    shopBean.getData().get(i).getList().get(j).setChecked(true);

                    numAll += 1;
                    priceAll += shopBean.getData().get(i).getList().get(j).getPrice() * numAll;

                }
            }
            shopCarAdapter.notifyDataSetChanged();
        } else {
            imgck.setImageResource(R.drawable.ck_no);
            for (int i = 0; i < shopBean.getData().size(); i++) {
                for (int j = 0; j < shopBean.getData().get(i).getList().size(); j++) {
                    shopBean.getData().get(i).getList().get(j).setChecked(false);

                }
            }
            numAll = 0;
            priceAll = 0;
            shopCarAdapter.notifyDataSetChanged();
        }
        flag = !flag;
        setNumAndPrice();
    }

    //网络请求成功
    @Override
    public void Success(int type, String data) {
        super.Success(type, data);
        Log.d("ShopFragmentPersenter", data);
        if ("null".equals(data)) {
            Toast.makeText(mcontext, "请在购物车添加商品呦！！", Toast.LENGTH_SHORT).show();
            return;
        }
        Gson gson = new Gson();
        shopBean = gson.fromJson(data, ShopBean.class);
        shopCarAdapter = new ShopCarAdapter(mcontext, shopBean.getData());
        shopCarAdapter.setListener(this);
        xlistview.setAdapter(shopCarAdapter);
    }

    //网络请求失败
    @Override
    public void fila(String msg) {
        super.fila(msg);

    }

    //初始化控件
    private void initView() {
        xlistview = (XListView) get(R.id.shop_xlistview);
        imgck = (ImageView) get(R.id.img_ck);
        tprice = (TextView) get(R.id.total_price);
        tnum = (TextView) get(R.id.total_num);

    }

    /*获取上下文*/
    @Override
    public void inicontext(Context context) {
        super.inicontext(context);
        this.mcontext = context;
    }

    /*
     * 数量回调
     * */
    @Override
    public void comeData(int num) {
        comment(num);
    }

    /*
     *所有价格与数量的方法
     * */
    public void comment(int num) {
        numAll = 0;
        priceAll = 0.0;
        List<ShopBean.DataBean> data = shopBean.getData();
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).getList().size(); j++) {
                if (data.get(i).getList().get(j).isChecked()) {
                    numAll += 1;
                    priceAll += data.get(i).getList().get(j).getPrice() * num;
                }
            }
        }
        setNumAndPrice();
    }

    /*
     *所有价格与数量赋值
     * */
    private void setNumAndPrice() {
        tnum.setText("商品数量:" + "(" + numAll + ")");
        tprice.setText("合计:￥" + priceAll + "");
    }

    public void notifyChange() {
        boolean isLogin = (boolean) SpUtil.getInserter(mcontext).getSpData("isLogin", false);
        if (!isLogin) {
            Toast.makeText(mcontext, "请先登陆", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> map = new HashMap<>();
        int uid = (int) SpUtil.getInserter(mcontext).getSpData("uid", 0);
        map.put("uid", uid + "");
        Log.d("ShopFragmentPersenter", uid + "???");
        getString("/product/getCarts", map, MERCHANT_URL);
    }
}
