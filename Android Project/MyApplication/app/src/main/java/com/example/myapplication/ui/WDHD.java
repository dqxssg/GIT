package com.example.myapplication.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class WDHD extends AppCompatActivity {

    private TextView ybm;
    private TextView ywc;
    private TextView yqx;
    private TextView xs;
    private ImageView back;
    private TextView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wdhd);
        initView();
        header.setText("我的活动");
        back.setOnClickListener(v -> {
            finish();
        });
        text(ybm, ywc, yqx);
        xs.setText("暂无已报名");
        ybm.setOnClickListener(v -> {
            text(ybm, ywc, yqx);
            xs.setText("暂无已报名");
        });
        ywc.setOnClickListener(v -> {
            text(ywc, ybm, yqx);
            xs.setText("暂无已完成");
        });
        yqx.setOnClickListener(v -> {
            text(yqx, ywc, ybm);
            xs.setText("暂无已取消");
        });
    }

    private void initView() {
        ybm = (TextView) findViewById(R.id.ybm);
        ywc = (TextView) findViewById(R.id.ywc);
        yqx = (TextView) findViewById(R.id.yqx);
        xs = (TextView) findViewById(R.id.xs);
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
    }

    public void text(TextView t1, TextView t2, TextView t3) {
        t1.setTextColor(Color.parseColor("#333333"));
        t2.setTextColor(Color.parseColor("#666666"));
        t3.setTextColor(Color.parseColor("#666666"));
    }
}
