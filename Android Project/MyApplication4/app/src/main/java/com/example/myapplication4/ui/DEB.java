package com.example.myapplication4.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;

public class DEB extends AppCompatActivity {
    public static Activity eactivity;
    private TextView back;
    private TextView header;
    private TextView xs;
    private TextView xyb;
    private TextView fh;
    private DatePicker datepicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deb);
        eactivity = this;
        Bundle bundle = this.getIntent().getExtras();
        int i = bundle.getInt("i");
        int id = bundle.getInt("id");
        initView();
        header.setText("第二步");
        fh.setOnClickListener(v -> {
            finish();
        });
        back.setOnClickListener(v -> {
            finish();
        });
        int year = datepicker.getYear();
        int month = datepicker.getMonth();
        int dat = datepicker.getDayOfMonth();
        datepicker.init(year, month, dat, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String data = year + "-" + monthOfYear + "-" + dayOfMonth;
                xs.setText(data);
                xyb.setOnClickListener(v -> {
                    Intent intent = new Intent(DEB.this, DSB.class);
                    intent.putExtra("i", i);
                    intent.putExtra("id", id);
                    intent.putExtra("data", data);
                    startActivity(intent);
                });
            }
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        xs = (TextView) findViewById(R.id.xs);
        xyb = (TextView) findViewById(R.id.xyb);
        fh = (TextView) findViewById(R.id.fh);
        datepicker = (DatePicker) findViewById(R.id.datepicker);
    }
}
