package com.example.myapplication4.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;

public class WDHD extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView wc;
    private TextView bm;
    private TextView qx;
    private TextView xs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wdhd);
        initView();
        header.setText("我的活动");
        back.setOnClickListener(v -> {
            finish();
        });
        //点击
        DJ();
        textColor(wc, bm, qx);
        xs.setText("暂无已完成活动");
    }

    private void DJ() {
        wc.setOnClickListener(v -> {
            textColor(wc, bm, qx);
            xs.setText("暂无已完成活动");
        });
        bm.setOnClickListener(v -> {
            textColor(bm, wc, qx);
            xs.setText("暂无已报名活动");
        });
        qx.setOnClickListener(v -> {
            textColor(qx, bm, wc);
            xs.setText("暂无已取消活动");
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        wc = (TextView) findViewById(R.id.wc);
        bm = (TextView) findViewById(R.id.bm);
        qx = (TextView) findViewById(R.id.qx);
        xs = (TextView) findViewById(R.id.xs);
    }

    //字体颜色改变
    private void textColor(TextView t1, TextView t2, TextView t3) {
        t1.setTextColor(Color.parseColor("#002fa7"));
        t2.setTextColor(Color.parseColor("#000000"));
        t3.setTextColor(Color.parseColor("#000000"));
    }
}
