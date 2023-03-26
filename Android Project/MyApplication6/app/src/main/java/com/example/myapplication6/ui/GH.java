package com.example.myapplication6.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication6.R;

public class GH extends AppCompatActivity {
    public static Activity activity;
    private TextView back;
    private TextView header;
    private TextView zj;
    private TextView pt;
    private HorizontalScrollView ptjm;
    private TextView b;
    private TextView j;
    private TextView s;
    private TextView sy;
    private TextView ss;
    private TextView sw;
    private TextView sl;
    private TextView zjjm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gh);
        activity = this;
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        String ks = bundle.getString("ks");
        String mony = bundle.getString("mony");
        String mmz = bundle.getString("mmz");
        initView();
        header.setText("挂号");
        back.setOnClickListener(v -> {
            finish();
        });
        pt.setOnClickListener(v -> {
            ptjm.setVisibility(View.VISIBLE);
            zjjm.setVisibility(View.INVISIBLE);
        });
        zj.setOnClickListener(v -> {
            zjjm.setVisibility(View.VISIBLE);
            ptjm.setVisibility(View.INVISIBLE);
        });
        b.setOnClickListener(v -> {
            Intent intent = new Intent(GH.this, GHH.class);
            intent.putExtra("id", id);
            intent.putExtra("ks", ks);
            intent.putExtra("mmz", mmz);
            intent.putExtra("mony", mony);
            intent.putExtra("name", b.getText().toString());
            startActivity(intent);
        });
        j.setOnClickListener(v -> {
            Intent intent = new Intent(GH.this, GHH.class);
            intent.putExtra("ks", ks);
            intent.putExtra("id", id);
            intent.putExtra("mmz", mmz);
            intent.putExtra("mony", mony);
            intent.putExtra("name", j.getText().toString());
            startActivity(intent);
        });
        s.setOnClickListener(v -> {
            Intent intent = new Intent(GH.this, GHH.class);
            intent.putExtra("ks", ks);
            intent.putExtra("mmz", mmz);
            intent.putExtra("mony", mony);
            intent.putExtra("id", id);
            intent.putExtra("name", s.getText().toString());
            startActivity(intent);
        });
        sy.setOnClickListener(v -> {
            Intent intent = new Intent(GH.this, GHH.class);
            intent.putExtra("id", id);
            intent.putExtra("mmz", mmz);
            intent.putExtra("mony", mony);
            intent.putExtra("ks", ks);
            intent.putExtra("name", sy.getText().toString());
            startActivity(intent);
        });
        ss.setOnClickListener(v -> {
            Intent intent = new Intent(GH.this, GHH.class);
            intent.putExtra("id", id);
            intent.putExtra("mony", mony);
            intent.putExtra("name", ss.getText().toString());
            intent.putExtra("ks", ks);
            intent.putExtra("mmz", mmz);
            startActivity(intent);
        });
        sw.setOnClickListener(v -> {
            Intent intent = new Intent(GH.this, GHH.class);
            intent.putExtra("id", id);
            intent.putExtra("name", sw.getText().toString());
            intent.putExtra("ks", ks);
            intent.putExtra("mony", mony);
            intent.putExtra("mmz", mmz);
            startActivity(intent);
        });
        sl.setOnClickListener(v -> {
            Intent intent = new Intent(GH.this, GHH.class);
            intent.putExtra("id", id);
            intent.putExtra("name", sl.getText().toString());
            intent.putExtra("ks", ks);
            intent.putExtra("mony", mony);
            intent.putExtra("mmz", mmz);
            startActivity(intent);
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        zj = (TextView) findViewById(R.id.zj);
        pt = (TextView) findViewById(R.id.pt);
        ptjm = (HorizontalScrollView) findViewById(R.id.ptjm);
        b = (TextView) findViewById(R.id.b);
        j = (TextView) findViewById(R.id.j);
        s = (TextView) findViewById(R.id.s);
        sy = (TextView) findViewById(R.id.sy);
        ss = (TextView) findViewById(R.id.ss);
        sw = (TextView) findViewById(R.id.sw);
        sl = (TextView) findViewById(R.id.sl);
        zjjm = (TextView) findViewById(R.id.zjjm);
    }
}
