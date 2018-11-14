package com.bwie.dongdong.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.dongdong.R;
import com.squareup.picasso.Picasso;

public class ShoCarView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private ImageView select;
    private ImageView shopiamge;
    private EditText edNum;
    private TextView txtTitle;
    private TextView txtPrice;
    private TextView txtMinus;
    private TextView txtPush;
    private int num = 1;
    private boolean ischeck;

    public ShoCarView(Context context) {
        super(context);
        init(context);
    }

    public ShoCarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShoCarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context mContext) {
        this.mContext = mContext;
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_shopcar_view, null);
        //初始化控件
        initWeght(view);
        addView(view);
    }

    private void initWeght(View view) {
        select = (ImageView) view.findViewById(R.id.iv_select);
        shopiamge = (ImageView) view.findViewById(R.id.iv_shop_iamge);
        txtTitle = (TextView) view.findViewById(R.id.iv_title);
        txtPrice = (TextView) view.findViewById(R.id.iv_price);
        txtMinus = (TextView) view.findViewById(R.id.iv_minus);

        txtPush = (TextView) view.findViewById(R.id.iv_push);
        edNum = (EditText) view.findViewById(R.id.iv_num);
        txtMinus.setOnClickListener(this);
        txtPush.setOnClickListener(this);
        select.setOnClickListener(this);
    }

    //设置选中未选中的图片
    public void setSelectImageResource(int resource) {
        select.setImageResource(resource);
    }

    //设置商品图片
    public void setShopImageUrl(String url) {
        Picasso.with(mContext).load(url).fit().into(shopiamge);
    }

    //设置商品名称
    public void setShopTitle(String title) {
        txtTitle.setText(title);
    }

    //设置商品价格
    public void setShopPrice(String price) {
        txtPrice.setText(price);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_push:
                num++;
                wartEd(num);
                break;
            case R.id.iv_minus:
                num--;
                wartEd(num);
                break;
            case R.id.iv_select:
                if (ischeck == true) {
                    ischeck = false;
                    select.setImageResource(R.drawable.ck_no);
                } else {
                    ischeck = true;
                    select.setImageResource(R.drawable.ck_yes);
                }
                listener.selectBack(ischeck);
                break;
        }
    }


    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
        if (ischeck) {
            select.setImageResource(R.drawable.ck_yes);
        } else {
            select.setImageResource(R.drawable.ck_no);
        }
    }

    public void wartEd(int num) {
        edNum.setText(num + "");
        listener.dataBack(num);
    }

    //自定义接口，回调数据

    public interface DataBackListener {
        void dataBack(int data);

        void selectBack(boolean ischeck);
    }

    private DataBackListener listener;

    //实例化接口对象
    public void setListener(DataBackListener listener) {
        this.listener = listener;
    }

    //实例化商品数量
    public void setNum(int num) {
        this.num = num;
        edNum.setText(num + "");
    }
}