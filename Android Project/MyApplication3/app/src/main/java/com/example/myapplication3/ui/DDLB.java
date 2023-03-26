package com.example.myapplication3.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;

public class DDLB extends AppCompatActivity {
    private TextView wzf;
    private TextView yzf;
    private TextView xs;
    private ImageView back;
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
        Text(wzf, yzf);
        wzf.setOnClickListener(v -> {
        });
        yzf.setOnClickListener(v -> {
            Text(yzf, wzf);
        });
    }

    public void Text(TextView t1, TextView t2) {
        t1.setTextColor(Color.parseColor("#002fa7"));
        t2.setTextColor(Color.parseColor("#000000"));
    }

    private void initView() {
        wzf = (TextView) findViewById(R.id.wzf);
        yzf = (TextView) findViewById(R.id.yzf);
        xs = (TextView) findViewById(R.id.xs);
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
    }
}
