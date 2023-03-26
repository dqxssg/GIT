package com.example.test.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class DDLB extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private TextView wzf;
    private TextView yzf;
    private TextView bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ddlb);
        initView();
        header.setText("订单列表");
        back.setOnClickListener(view -> {
            finish();
        });
        Text(wzf, yzf);
        bt.setText("暂无未支付订单");
        wzf.setOnClickListener(view -> {
            Text(wzf, yzf);
            bt.setText("暂无未支付订单");
        });
        yzf.setOnClickListener(view -> {
            Text(yzf, wzf);
            bt.setText("暂无已支付订单");
        });
    }

    //字体颜色变化
    public void Text(TextView t1, TextView t2) {
        t1.setTextColor(Color.parseColor("#002fa7"));
        t2.setTextColor(Color.parseColor("#000000"));
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        wzf = (TextView) findViewById(R.id.wzf);
        yzf = (TextView) findViewById(R.id.yzf);
        bt = (TextView) findViewById(R.id.bt);
    }
}
