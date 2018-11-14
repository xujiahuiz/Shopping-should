package com.bwie.dongdong.fragmentp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.dongdong.R;
import com.bwie.dongdong.acitivity.CompileActivity;
import com.bwie.dongdong.acitivity.LoginActivity;
import com.bwie.dongdong.entity.SpUtil;
import com.bwie.dongdong.mvp.view.AppIDetagate;

/**
 * 登录
 **/
public class MeFragmentPersenter extends AppIDetagate {
    private Context mcontext;
    private TextView dname;
    private ImageView dpic;
    private RelativeLayout drela;

    @Override
    protected int getLayout() {
        return R.layout.layout_me_fragment;
    }

    @Override
    public void initData() {
        super.initData();
        initView();
        setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.display_rela:
                        boolean isLogin = (boolean) SpUtil.getInserter(mcontext).getSpData("isLogin", false);
                        if (isLogin) {
                            Intent intent = new Intent(mcontext, CompileActivity.class);
                            mcontext.startActivity(intent);
                        } else {
                            Intent intent = new Intent(mcontext, LoginActivity.class);
                            mcontext.startActivity(intent);
                        }
                        break;
                }
            }
        }, R.id.display_rela);
    }

    private void initView() {
        dname = (TextView) get(R.id.display_name);
        dpic = (ImageView) get(R.id.display_pic);
        drela = (RelativeLayout) get(R.id.display_rela);
    }

    @Override
    public void inicontext(Context context) {
        super.inicontext(context);
        this.mcontext = context;

    }


    public void setUserName(String userName) {
        dname.setText(userName);

    }
}
