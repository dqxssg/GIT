package com.example.myapplication10.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;

public class SHJF extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView sf;
    private TextView df;
    private TextView hhgl;
    private TextView hhglBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shjf);
        initView();
        header.setText("生活缴费");
        back.setOnClickListener(v -> {
            finish();
        });
        sf.setOnClickListener(v -> {
            startActivity(new Intent(SHJF.this, SF.class));
        });
        df.setOnClickListener(v -> {
            startActivity(new Intent(SHJF.this, DF.class));
        });
        hhgl.setOnClickListener(v -> {
            startActivity(new Intent(SHJF.this, HHGL.class));
        });
        hhglBtn.setOnClickListener(v -> {
            startActivity(new Intent(SHJF.this, HHGL.class));
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        sf = (TextView) findViewById(R.id.sf);
        df = (TextView) findViewById(R.id.df);
        hhgl = (TextView) findViewById(R.id.hhgl);
        hhglBtn = (TextView) findViewById(R.id.hhgl_btn);
    }
}
