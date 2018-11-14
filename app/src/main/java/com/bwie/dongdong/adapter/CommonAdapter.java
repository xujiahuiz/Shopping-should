package com.bwie.dongdong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context mContext; //定义Context对象
    protected List<T> datas = new ArrayList<>();    //List集合使用了T的应用可以把它当成一个类
    protected LayoutInflater mInflater;

    //实例化布局对象
    public CommonAdapter(Context mContext, List<T> datas) {
        this.mContext = mContext;
        this.datas = datas;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<T> list) {
        this.datas = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IViewHolder viewHoler = IViewHolder.getInstance(mContext, parent, getLayoutId(), convertView, position);
        convert(viewHoler, getItem(position));
        return viewHoler.getConverview();
    }

    //写一个抽象方法把viewholder 和 当前的数据提供出去
    public abstract void convert(IViewHolder holer, T t);

    public abstract int getLayoutId();
}




