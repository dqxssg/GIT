package com.example.zhcs.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhcs.R;

public class DDLB extends AppCompatActivity {
    private TextView wzf;
    private TextView yzf;
    private TextView xs;
    private TextView back;
    private TextView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddlb);
        initView();
        header.setText("订单列表");
        back.setOnClickListener(v -> {
            finish();
        });
        xs.setText("暂无未支付");
        text(wzf, yzf);
        wzf.setOnClickListener(v -> {
            text(wzf, yzf);
            xs.setText("暂无未支付");
        });
        yzf.setOnClickListener(v -> {
            text(yzf, wzf);
            xs.setText("暂无已支付");
        });
    }

    private void initView() {
        wzf = (TextView) findViewById(R.id.wzf);
        yzf = (TextView) findViewById(R.id.yzf);
        xs = (TextView) findViewById(R.id.xs);
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
    }

    //点击字体颜色改变
    public void text(TextView t1, TextView t2) {
        t1.setTextColor(Color.parseColor("#002fa7"));
        t2.setTextColor(Color.parseColor("#000000"));
    }
}

