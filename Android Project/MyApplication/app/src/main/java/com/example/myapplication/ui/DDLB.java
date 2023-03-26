package com.example.myapplication.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class DDLB extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private TextView y;
    private TextView w;
    private TextView xs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddlb);
        initView();
        header.setText("订单列表");
        back.setOnClickListener(v -> {
            finish();
        });
        text(w, y);
        xs.setText("暂无未支付订单");
        w.setOnClickListener(v -> {
            text(w, y);
            xs.setText("暂无未支付订单");
        });
        y.setOnClickListener(v -> {
            text(y, w);
            xs.setText("暂无已支付订单");
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        y = (TextView) findViewById(R.id.y);
        w = (TextView) findViewById(R.id.w);
        xs = (TextView) findViewById(R.id.xs);
    }

    //字体颜色变换
    public void text(TextView textView1, TextView textView2) {
        textView1.setTextColor(Color.parseColor("#333333"));
        textView2.setTextColor(Color.parseColor("#666666"));
    }
}
