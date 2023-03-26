package com.example.myapplication3.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;

public class YJFK extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private EditText sr;
    private TextView tj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yjfk);
        initView();
        header.setText("意见反馈");
        back.setOnClickListener(v -> {
            finish();
        });
        tj.setOnClickListener(v -> {
            runOnUiThread(() -> {
                Toast.makeText(this, "反馈成功", Toast.LENGTH_SHORT).show();
                sr.setText("");
            });
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        sr = (EditText) findViewById(R.id.sr);
        tj = (TextView) findViewById(R.id.tj);
    }
}
