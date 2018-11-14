package com.bwie.dongdong.activityp;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.dongdong.R;
import com.bwie.dongdong.mvp.view.AppIDetagate;

import org.w3c.dom.Text;

public class CompilePersenter extends AppIDetagate {
    private Context mcontext;
    private ImageView cpic;
    private TextView csex;
    private TextView cname;

    @Override
    protected int getLayout() {
        return R.layout.activity_compile;
    }

    @Override
    public void initData() {
        super.initData();
        initView();
    }

    private void initView() {
        cpic =(ImageView) get(R.id.compile_pic);
        cname =(TextView) get(R.id.compile_name);
        csex  =(TextView) get(R.id.compile_sex);
    }

    @Override
    public void inicontext(Context context) {
        super.inicontext(context);
        this.mcontext=context;
    }
}