package com.bwie.dongdong.activityp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.bwie.dongdong.R;
import com.bwie.dongdong.acitivity.ShopActivity;
import com.bwie.dongdong.entity.SpUtil;
import com.bwie.dongdong.mvp.view.AppIDetagate;

import java.util.HashMap;
import java.util.Map;

public class ShopPersenter extends AppIDetagate {
    private Context mcontext;
    private WebView bwebview;
    private Button btbuy;
    private Button btadd;
    private static final int ADD_URL = 1;
    private int pid;

    @Override
    protected int getLayout() {
        return R.layout.activity_shop;
    }

    @Override
    public void initData() {
        super.initData();
        initView();
        Intent intent = ((ShopActivity) mcontext).getIntent();
        Bundle extras = intent.getExtras();
        String descurl = extras.getString("descurl");
        pid = extras.getInt("pid");
        Log.d("tag1107", descurl + "_____" + pid);
        bwebview.setWebViewClient(new WebViewClient());
        bwebview.getSettings().setJavaScriptEnabled(true);
        bwebview.loadUrl(descurl);
        setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.addshop:
                        int uid = (int) SpUtil.getInserter(mcontext).getSpData("uid", -1);
                        if (uid == -1) {
                            Toast.makeText(mcontext, "请检查是否登陆", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Log.d("ShopPersenter", uid + "");
                        Map<String, String> map = new HashMap<>();
                        map.put("pid", pid + "");
                        map.put("uid", uid + "");
                        getString("/product/addCart", map, ADD_URL);
                        break;
                }
            }
        }, R.id.addshop);
    }

    @Override
    public void Success(int type, String data) {
        super.Success(type, data);
        switch (type) {
            case ADD_URL:
                Toast.makeText(mcontext, "添加成功！", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void fila(String msg) {
        super.fila(msg);
    }

    private void initView() {
        bwebview = (WebView) get(R.id.buywebview);
        btbuy = (Button) get(R.id.buy);
        btadd = (Button) get(R.id.addshop);
    }

    @Override
    public void inicontext(Context context) {
        super.inicontext(context);
        this.mcontext = context;

    }
}
