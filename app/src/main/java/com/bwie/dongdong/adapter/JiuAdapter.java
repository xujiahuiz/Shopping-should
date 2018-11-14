package com.bwie.dongdong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.dongdong.R;
import com.bwie.dongdong.bean.JiuBean;

import java.util.ArrayList;
import java.util.List;
/**
 * 作者:徐家辉
 * 时间:2018/10/30
 * 作用:JiuAdapter(九宮格)
 */
public class JiuAdapter extends PagerAdapter {
    private Context context;
    private List<JiuBean.DataBean> onepage = new ArrayList<>();
    private List<JiuBean.DataBean> towpage = new ArrayList<>();
    private int page = 0;

    public JiuAdapter(Context mcontext, List<JiuBean.DataBean> onepage, List<JiuBean.DataBean> towpage, int page) {
        this.context = mcontext;
        this.onepage = onepage;
        this.towpage = towpage;
        this.page = page;
    }


    @Override
    public int getCount() {
        return page;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.layout_jiu, null);
        RecyclerView recycler = view.findViewById(R.id.hide_jiureceiver);
        JiuRecyAdapter adapter = new JiuRecyAdapter(context);

        // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
        recycler.setLayoutManager(gridLayoutManager);

        recycler.setAdapter(adapter);
        if (position == 0) {
            adapter.setList(onepage);
        } else if (position == 1) {
            adapter.setList(towpage);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
