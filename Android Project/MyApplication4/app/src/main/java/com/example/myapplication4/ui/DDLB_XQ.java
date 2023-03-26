package com.example.myapplication4.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;

public class DDLB_XQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView ddh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddlb_xq);
        Bundle bundle=this.getIntent().getExtras();
        String no = bundle.getString("no");
        initView();
        header.setText("订单详情");
        back.setOnClickListener(v -> {
            finish();
        });
        ddh.setText("订单号："+no);

    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        ddh = (TextView) findViewById(R.id.ddh);
    }
}
