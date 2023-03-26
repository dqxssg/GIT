package com.example.myapplication.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ZHJG extends AppCompatActivity {
    String[] item = {"冀", "晋", "黑", "吉", "辽", "苏", "浙", "皖", "闽", "赣", "鲁", "豫", "鄂", "湘", "粤", "琼", "川", "黔", "滇", "陕", "甘", "青", "台", "内蒙古", "桂", "藏", "宁", "新", "京", "沪", "津", "渝", "港", "澳"};
    private ImageView back;
    private TextView header;
    private Spinner spinner;
    private EditText edit;
    private TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhjg);
        initView();
        edit.clearFocus();
        header.setText("违章查询");
        back.setOnClickListener(v -> {
            finish();
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ZHJG.this, android.R.layout.simple_spinner_item, item);
        spinner.setAdapter(adapter);
        text.setOnClickListener(v -> {
            Toast.makeText(this, "暂未查到该车违章信息", Toast.LENGTH_SHORT).show();
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        spinner = (Spinner) findViewById(R.id.spinner);
        edit = (EditText) findViewById(R.id.edit);
        text = (TextView) findViewById(R.id.text);
    }
}
