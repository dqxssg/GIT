package com.example.zhcs.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zhcs.R;

public class FW extends AppCompatActivity {
    private TextView back;
    private TextView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fw);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        header.setText(name);
        back.setOnClickListener(v -> {
            finish();
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
    }
}
