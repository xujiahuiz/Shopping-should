package com.bwie.dongdong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwie.dongdong.R;

import java.util.List;

/**
 * 作者:徐家辉
 * 时间:2018/10/30
 * 作用:StartAdapter(引导页adapter)
 */
public class StartAdapter extends PagerAdapter {
    private List<Integer> imgs;
    private Context mcontext;


    public StartAdapter(List<Integer> imgs, Context mcontext) {
        this.imgs = imgs;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = View.inflate(mcontext, R.layout.layout_start_item, null);
        ImageView image = (ImageView) inflate.findViewById(R.id.start_img);
        image.setImageResource(imgs.get(position));
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
