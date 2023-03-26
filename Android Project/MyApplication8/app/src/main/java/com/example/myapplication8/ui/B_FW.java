package com.example.myapplication8.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication8.R;

public class B_FW extends AppCompatActivity {
    private TextView back;
    private TextView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_fw);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        initView();
        back.setOnClickListener(v -> {
            finish();
        });
        header.setText(name);
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
    }
}
