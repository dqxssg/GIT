package com.example.myapplication6.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication6.R;
import com.example.myapplication6.adapter.Adapter_SZTSGPL_LV;
import com.example.myapplication6.bean.S_SZTSGPL_LV;

import java.util.ArrayList;

public class SZTSG_PL extends AppCompatActivity {
    private ArrayList<S_SZTSGPL_LV> s_sztsgpl_lvs = new ArrayList<>();
    private Adapter_SZTSGPL_LV adapter_sztsgpl_lv;
    private TextView back;
    private TextView header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sztsg_pl);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("评论");
        back.setOnClickListener(v -> {
            finish();
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
    }
}
