package com.bwie.dongdong.activityp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwie.dongdong.R;
import com.bwie.dongdong.acitivity.ShowActivity;
import com.bwie.dongdong.adapter.StartAdapter;
import com.bwie.dongdong.mvp.view.AppIDetagate;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:徐家辉
 * 时间:2018/10/30
 * 作用:MainPersenter(欢迎页)
 */
public class MainPersenter extends AppIDetagate {

    private Context mcontext;
    private ViewPager viewpager;
    private List<Integer> imgs = new ArrayList<>();
    private LinearLayout mainPonit;
    private Button jump;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
        initView();
        StartAdapter startAdapter = new StartAdapter(imgs, mcontext);
        viewpager.setAdapter(startAdapter);
        pointe(0);
    }

    private void initView() {
        viewpager = (ViewPager) get(R.id.main_viewpager);
        mainPonit = (LinearLayout) get(R.id.main_point);
        jump = (Button) get(R.id.main_jump);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                pointe(i);
                if (i == imgs.size() - 1) {
                    jump.setText("立即跳转");
                    jump.setVisibility(View.VISIBLE);

                    setClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (v.getId()) {
                                case R.id.main_jump:
                                    mcontext.startActivity(new Intent(mcontext, ShowActivity.class));
                                    break;
                            }
                        }
                    }, R.id.main_jump);

                } else {
                    jump.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        imgs.add(R.drawable.start_01);
        imgs.add(R.drawable.start_02);
        imgs.add(R.drawable.start_03);
        imgs.add(R.drawable.start_04);
    }

    public void pointe(int page) {
        mainPonit.removeAllViews();
        for (int i = 0; i < imgs.size(); i++) {
            ImageView image = new ImageView(mcontext);
            if (page == i) {
                image.setImageResource(R.drawable.startsimle_yes);
            } else {
                image.setImageResource(R.drawable.startsimle_no);
            }
            mainPonit.addView(image);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) image.getLayoutParams();
            params.weight = 30;
            params.height = 30;
            image.setLayoutParams(params);
        }
    }

    @Override
    public void inicontext(Context context) {
        super.inicontext(context);
        this.mcontext = context;

    }
}
