package com.bwie.dongdong.activityp;

import android.content.Context;
import android.text.TextUtils;
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

public class RegisterPersenter extends AppIDetagate {
    private Context mcontext;
    private EditText rpass;
    private EditText rname;
    private EditText rqpass;
    private Button register;

    private static final int REGISTER_URL = 1;
    private String regidtername;


    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }


    @Override
    public void initData() {
        super.initData();
        initView();

        setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_register:
                        regidtername = rname.getText().toString().trim();
                        String registerpass1 = rpass.getText().toString().trim();
                        String registerpass2 = rqpass.getText().toString().trim();
                        useregister(regidtername, registerpass1, registerpass2);
                        break;

                }
            }
        }, R.id.bt_register);

    }

    private void useregister(String regidtername, String registerpass1, String registerpass2) {
        Toast.makeText(mcontext, "点击！！", Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(regidtername) && TextUtils.isEmpty(registerpass1) && TextUtils.isEmpty(registerpass2)) {
            Toast.makeText(mcontext, "请输入完整信息！！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!registerpass1.equals(registerpass2)) {
            Toast.makeText(mcontext, "请输入一致密码！！", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("Taggggg", regidtername + "====" + registerpass1);
       Map<String, String> map = new HashMap<>();
        map.put("mobile", regidtername);
        map.put("password", registerpass1);
        postString("/user/reg", map, REGISTER_URL);

    }

    @Override
    public void Success(int type, String data) {
        super.Success(type, data);
        switch (type) {
            case REGISTER_URL:
                Log.d("wwwww", data);
               Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(data, LoginBean.class);
                if ("0".equals(loginBean.getCode())) {

                    Toast.makeText(mcontext, "注册成功！！", Toast.LENGTH_SHORT).show();
                    ((RegisterActivity) mcontext).finish();

                } else {
                    Toast.makeText(mcontext, "注册失败！！", Toast.LENGTH_SHORT).show();

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
        rname = (EditText) get(R.id.register_name);
        rpass = (EditText) get(R.id.register_pass);
        rqpass = (EditText) get(R.id.register_qpass);
        register = (Button) get(R.id.bt_register);

    }


    @Override
    public void inicontext(Context context) {
        super.inicontext(context);
        this.mcontext = context;

    }
}
