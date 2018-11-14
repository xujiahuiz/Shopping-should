package com.bwie.dongdong.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class IViewHolder {

    private SparseArray<View> mViews; //用于存储View控件
    private int mPostion;             //postion当前的条目
    private View mConverview;
    private Context mcontext;

    //私有化构造器
    private IViewHolder(Context context, ViewGroup preant, int layoutId, int position) {
        mcontext = context;
        this.mPostion = position;
        this.mViews = new SparseArray<>();
        mConverview = LayoutInflater.from(context).inflate(layoutId, preant, false);
        /* mConverview = View.inflate(context, layoutId, preant);*/
        mConverview.setTag(this);
    }


    //单利模式获取ViewHolder对象
    public static IViewHolder getInstance(Context context, ViewGroup preant, int layoutId, View convertView, int position) {
        if (convertView == null) {
            return new IViewHolder(context, preant, layoutId, position);
        } else {
            IViewHolder viewHoler = (IViewHolder) convertView.getTag();
            viewHoler.mPostion = position;
            return viewHoler;
        }
    }


    //获取当前对象
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConverview.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


    //获取当前的converView
    public View getConverview() {
        return mConverview;
    }


    //设置文本
    public IViewHolder setText(int viewId, String context) {
        TextView tv = getView(viewId);
        tv.setText(context);
        return this;
    }


    //设置图片
    public IViewHolder setImageResource(int viewId, int resout) {
        ImageView im = getView(viewId);
        im.setImageResource(resout);
        return this;
    }

    //.......相应的添加自己想要的功能如获取网络图片等

    public IViewHolder setUrlImage(int viewId, String url) {
        ImageView view = getView(viewId);
        Picasso.with(mcontext).load(url).fit().into(view);
        return this;
    }


}
