package com.example.myapplication10.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication10.R;

public class DEB extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView xs;
    private DatePicker datepicker;
    private TextView xyb;
    private TextView fh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deb);
        Bundle bundle = this.getIntent().getExtras();
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
        datepicker = (DatePicker) findViewById(R.id.datepicker);
        xyb = (TextView) findViewById(R.id.xyb);
        fh = (TextView) findViewById(R.id.fh);
    }
}
