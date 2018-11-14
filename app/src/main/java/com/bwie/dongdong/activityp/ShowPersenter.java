package com.bwie.dongdong.activityp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.bwie.dongdong.R;
import com.bwie.dongdong.acitivity.ShowActivity;
import com.bwie.dongdong.fragment.HideFragment;
import com.bwie.dongdong.fragment.ListFragment;
import com.bwie.dongdong.fragment.MeFragment;
import com.bwie.dongdong.fragment.ShopFragment;
import com.bwie.dongdong.mvp.view.AppIDetagate;
import com.bwie.dongdong.tabview.TabView;
import com.bwie.dongdong.tabview.TabViewChild;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:徐家辉
 * 时间:2018/10/30
 * 作用:ShowPersenter(主页)
 */
public class ShowPersenter extends AppIDetagate {

    private Context mcontext;
    private ViewPager viewpager;
    private TabView tabview;
    private List<TabViewChild> tabChilds = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_show;
    }

    @Override
    public void initData() {
        super.initData();

        initView();
        TabViewChild tabViewChild1 = new TabViewChild(R.drawable.hide_yes, R.drawable.hide_no, "首页", new HideFragment());
        TabViewChild tabViewChild2 = new TabViewChild(R.drawable.list_yes, R.drawable.list_no, "列表", new ListFragment());
        TabViewChild tabViewChild3 = new TabViewChild(R.drawable.shop_yes, R.drawable.shop_no, "购物车", new ShopFragment());
        TabViewChild tabViewChild4 = new TabViewChild(R.drawable.me_yes, R.drawable.me_no, "我的", new MeFragment());
        tabChilds.add(tabViewChild1);
        tabChilds.add(tabViewChild2);
        tabChilds.add(tabViewChild3);
        tabChilds.add(tabViewChild4);
        tabview.setTabViewChild(tabChilds, ((ShowActivity) mcontext).getSupportFragmentManager());

    }

    private void initView() {

        tabview = (TabView) get(R.id.show_tabview);
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HideFragment());
        fragments.add(new ListFragment());
        fragments.add(new ShopFragment());
        fragments.add(new MeFragment());
    }

    @Override
    public void inicontext(Context context) {
        super.inicontext(context);
        this.mcontext = context;

    }
}
