package com.example.myapplication3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;

public class CXJL extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private LinearLayout line;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cxjl);
        initView();
        header.setText("添加简历");
        back.setOnClickListener(v -> {
            finish();
        });
        line.setOnClickListener(v -> {
            startActivity(new Intent(CXJL.this, BJJL.class));
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        line = (LinearLayout) findViewById(R.id.line);
    }
}
