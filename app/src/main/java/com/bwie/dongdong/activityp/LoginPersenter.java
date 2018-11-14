package com.bwie.dongdong.activityp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.dongdong.R;
import com.bwie.dongdong.acitivity.LoginActivity;
import com.bwie.dongdong.acitivity.RegisterActivity;
import com.bwie.dongdong.bean.LoginBean;
import com.bwie.dongdong.entity.SpUtil;
import com.bwie.dongdong.mvp.view.AppIDetagate;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginPersenter extends AppIDetagate {
    private Context mcontext;
    private EditText lname;
    private EditText lpass;
    private Button login;
    private TextView register;
    private static final int LOGIN_URL = 1;
    private String loginname;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }


    @Override
    public void initData() {
        super.initData();
        initView();

        setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_login:
                        loginname = lname.getText().toString().trim();
                        String loginpass = lpass.getText().toString().trim();
                        uselogin(loginname, loginpass);
                        break;
                    case R.id.txt_register:
                        mcontext.startActivity(new Intent(mcontext, RegisterActivity.class));
                        break;
                }
            }
        }, R.id.bt_login, R.id.txt_register);

    }

    private void uselogin(String loginname, String loginpass) {
        if (loginname.isEmpty() && loginpass.isEmpty()) {
            Toast.makeText(mcontext, "用户名或密码不能为空！！", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("Taggggg", loginname + "====" + loginpass);
        Map<String, String> map = new HashMap<>();
        map.put("mobile", loginname);
        map.put("password", loginpass);
        postString("/user/login", map, LOGIN_URL);

    }

    @Override
    public void Success(int type, String data) {
        super.Success(type, data);
        switch (type) {
            case LOGIN_URL:
                Log.d("wwwww", "登录");
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(data, LoginBean.class);
                if ("0".equals(loginBean.getCode())) {
                    Toast.makeText(mcontext, "登陆成功！！", Toast.LENGTH_SHORT).show();
                    SpUtil.getInserter(mcontext).saveData("lname", loginname).putInt("uid",loginBean.getData().getUid()).putString("token",loginBean.getData().getToken()).putString("mobile",loginBean.getData().getMobile()).putBoolean("isLogin", true).commit();
                    ((LoginActivity) mcontext).finish();

                } else {
                    Toast.makeText(mcontext, "登陆失败！！", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    @Override
    public void fila(String msg) {
        super.fila(msg);

        Log.d("eee", "失败");

    }

    private void initView() {
        lname = (EditText) get(R.id.login_name);
        lpass = (EditText) get(R.id.login_pass);
        login = (Button) get(R.id.bt_login);
        register = (TextView) get(R.id.txt_register);
    }


    @Override
    public void inicontext(Context context) {
        super.inicontext(context);
        this.mcontext = context;

    }
}
