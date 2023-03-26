package com.example.test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class DEB extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private TextView xs;
    private DatePicker date;
    private TimePicker time;
    private TextView xyb;
    private TextView fh;
    private String datee;
    private String timee;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deb);
        Bundle bundle = this.getIntent().getExtras();
        int i = bundle.getInt("i");
        int id = bundle.getInt("id");
        initView();
        header.setText("第二步");
        back.setOnClickListener(view -> {
            finish();
        });
        fh.setOnClickListener(view -> {
            finish();
        });
        int year = date.getYear();
        int mothe = date.getMonth();
        int day = date.getDayOfMonth();
        date.init(year, mothe, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                datee = year + "-" + monthOfYear + "-" + dayOfMonth;
                time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        timee = hourOfDay + ":" + minute;
                        xs.setText(datee + "   " + timee);
                        xyb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(DEB.this, DSB.class);
                                intent.putExtra("i", i);
                                intent.putExtra("id", id);
                                intent.putExtra("datee", datee);
                                intent.putExtra("timee", timee);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        xs = (TextView) findViewById(R.id.xs);
        date = (DatePicker) findViewById(R.id.date);
        time = (TimePicker) findViewById(R.id.time);
        xyb = (TextView) findViewById(R.id.xyb);
        fh = (TextView) findViewById(R.id.fh);
    }
}
