package com.bwie.dongdong.fragmentp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bwie.dongdong.R;
import com.bwie.dongdong.adapter.JiuAdapter;
import com.bwie.dongdong.adapter.ShopHomeAdapter;
import com.bwie.dongdong.bean.BannerBean;
import com.bwie.dongdong.bean.JiuBean;
import com.bwie.dongdong.bean.ShopBean;
import com.bwie.dongdong.mvp.view.AppIDetagate;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 作者:徐家辉
 * 时间:2018/10/30
 * 作用:HideFragmentPersenter(轮播+recyclerview)
 */
public class HideFragmentPersenter extends AppIDetagate {
    private Context mcontext;
    private BGABanner banner;
    private RecyclerView recycler;
    private List<String> images = new ArrayList<>();
    private List<String> desc = new ArrayList<>();
    private List<JiuBean.DataBean> onepage = new ArrayList<>();
    private List<JiuBean.DataBean> towpage = new ArrayList<>();
    private RecyclerView jiurecycler;
    private ViewPager jiuviewpager;
    private ShopHomeAdapter shopHomeAdapter;
    private static final int BANNER_URL = 1;
    private static final int JIU_URL = 2;
    private static final int FALLS_URL = 3;

    @Override
    protected int getLayout() {
        return R.layout.layout_hide_fragment;
    }

    @Override
    public void initData() {
        super.initData();
        initView();
        /**
         * Banner图
         * */
        getString("/ad/getAd", null, BANNER_URL);
        getString("/product/getCatagory", null, JIU_URL);
        getString("/product/getCarts?uid=71", null, FALLS_URL);


    }

    @Override
    public void Success(int type, String data) {
        super.Success(type, data);
        switch (type) {
            /**
             * 轮播图
             * */
            case BANNER_URL:

                Gson gson = new Gson();
                BannerBean bannerBean = gson.fromJson(data, BannerBean.class);
                List<BannerBean.DataBean> data1 = bannerBean.getData();
                for (int i = 0; i < data1.size() - 1; i++) {
                    String icon = data1.get(i).getIcon();
                    String replace = icon.replace("https", "http");
                    images.add(replace);
                    desc.add(data1.get(i).getTitle());
                }
                //banner图
                banner.setData(images, desc);
                banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
                    @Override
                    public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
                        Picasso.with(mcontext).load(model).into(itemView);
                    }
                });

                break;

            /**
             * 九宫格
             * */
            case JIU_URL:
                Gson gson1 = new Gson();
                JiuBean jiuBean = gson1.fromJson(data, JiuBean.class);
                for (int i = 0; i < jiuBean.getData().size(); i++) {
                    if (i >= 8 && i < 16) {
                        towpage.add(jiuBean.getData().get(i));
                    } else if (i < 8) {
                        onepage.add(jiuBean.getData().get(i));
                    }
                }
                JiuAdapter jiuAdapter = new JiuAdapter(mcontext, onepage, towpage, 2);
                jiuviewpager.setAdapter(jiuAdapter);
                break;
            /**
             * 瀑布流
             * */
            case FALLS_URL:
                if (data.contains("<")) {
                    return;
                }
                Gson gson2 = new Gson();
                ShopBean shopBean = gson2.fromJson(data, ShopBean.class);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycler.setLayoutManager(linearLayoutManager);
                recycler.setAdapter(shopHomeAdapter);
                shopHomeAdapter.setList(shopBean.getData());
                break;
        }
    }

    @Override
    public void fila(String msg) {
        super.fila(msg);

    }

    private void initView() {
        banner = (BGABanner) get(R.id.hide_banner);
        recycler = (RecyclerView) get(R.id.hide_receiver);
        jiuviewpager = (ViewPager) get(R.id.hide_jiuviewpager);
        shopHomeAdapter = new ShopHomeAdapter(mcontext);
    }

    @Override
    public void inicontext(Context context) {
        super.inicontext(context);
        this.mcontext = context;

    }
}
