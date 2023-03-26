package com.example.myapplication3.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;

public class DEB extends AppCompatActivity {
    private String date;
    private String time;
    private ImageView back;
    private TextView header;
    private TextView xyb;
    private TextView fh;
    private TextView xs;
    private DatePicker datepicker;
    private TimePicker timepicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deb);
        Bundle bundle = this.getIntent().getExtras();
        int i = bundle.getInt("i");
        int id = bundle.getInt("id");
        initView();
        header.setText("第二步");
        back.setOnClickListener(v -> {
            finish();
        });
        fh.setOnClickListener(v -> {
            finish();
        });
        int year = datepicker.getYear();
        int month = datepicker.getMonth();
        int dayOfMonth = datepicker.getDayOfMonth();
        datepicker.init(year, month, dayOfMonth, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date = year + "-" + monthOfYear + "-" + dayOfMonth;
                timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        time = hourOfDay + ":" + minute;
                        xs.setText(date + "\u3000" + time);
                        xyb.setOnClickListener(v -> {
                            Intent intent = new Intent(DEB.this, DSB.class);
                            intent.putExtra("i", i);
                            intent.putExtra("id", id);
                            intent.putExtra("date", date);
                            intent.putExtra("time", time);
                            startActivity(intent);
                        });
                    }
                });
            }
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        xyb = (TextView) findViewById(R.id.xyb);
        fh = (TextView) findViewById(R.id.fh);
        xs = (TextView) findViewById(R.id.xs);
        datepicker = (DatePicker) findViewById(R.id.datepicker);
        timepicker = (TimePicker) findViewById(R.id.timepicker);
    }
}
