package com.example.myapplication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.Adapter_XX;
import com.example.myapplication.bean.ShuJv_XX;

import java.util.ArrayList;

public class SHJF extends AppCompatActivity {
    private ArrayList<ShuJv_XX> shuJv_xxes = new ArrayList<>();
    private Adapter_XX adapter_xx;
    private ImageView back;
    private TextView header;
    private ImageView czjl;
    private RadioGroup radio0;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private EditText sr;
    private TextView cx;
    private ListView listview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shjf);
        initView();
        sr.clearFocus();
        header.setText("生活缴费");
        back.setOnClickListener(v -> {
            finish();
        });
        czjl.setOnClickListener(v -> {
            startActivity(new Intent(SHJF.this, JFJL.class));
        });
        radio0.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                cx.setOnClickListener(v -> {
                    Intent intent = new Intent(SHJF.this, JFJM.class);
                    intent.putExtra("yys", radioButton.getText().toString());
                    intent.putExtra("sjh", sr.getText().toString());
                    startActivity(intent);
                });
                System.out.println();
            }
        });
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        String yys = sp.getString("yys", "");
        String sjh = sp.getString("sjh", "");
        String sj = sp.getString("sj", "");
        shuJv_xxes.add(new ShuJv_XX(yys, sjh, sj));
        adapter_xx = new Adapter_XX(SHJF.this, shuJv_xxes);
        runOnUiThread(() -> {
            listview.setAdapter(adapter_xx);
                listview.setOnItemClickListener((parent, view, position, id) -> {
                    sr.setText(shuJv_xxes.get(position).getSjh());
            });
    });
}

    @Override
    protected void onResume() {
        super.onResume();
//        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
//        String yys = sp.getString("yys", "");
//        String sjh = sp.getString("sjh", "");
//        String sj = sp.getString("sj", "");
//        shuJv_xxes.add(new ShuJv_XX(yys, sjh, sj));
        adapter_xx = new Adapter_XX(SHJF.this, shuJv_xxes);
        runOnUiThread(() -> {
            listview.setAdapter(adapter_xx);
                listview.setOnItemClickListener((parent, view, position, id) -> {
                    sr.setText(shuJv_xxes.get(position).getSjh());
                });
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        czjl = (ImageView) findViewById(R.id.czjl);
        radio0 = (RadioGroup) findViewById(R.id.radio0);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        sr = (EditText) findViewById(R.id.sr);
        cx = (TextView) findViewById(R.id.cx);
        listview = (ListView) findViewById(R.id.listview);
    }
}
