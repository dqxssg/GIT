package com.example.myapplication7.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication7.R;

public class DDLB_XQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView ddh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddlb_xq);
        Bundle b = this.getIntent().getExtras();
        String no = b.getString("no");
        initView();
        header.setText("详情");
        back.setOnClickListener(v -> {
            finish();
        });
        ddh.setText("订单号：" + no);
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        ddh = (TextView) findViewById(R.id.ddh);
    }
}


