package com.example.test.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.test.R;
import com.example.test.adapter.ViewPager2;
import com.example.test.fragment.ZGZ_A;
import com.example.test.fragment.ZGZ_B;
import com.example.test.fragment.ZGZ_C;

import java.util.ArrayList;

public class ZGZ extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ViewPager2 viewPager2;
    private ImageView back;
    private TextView header;
    private TextView zgz;
    private TextView tdjl;
    private TextView grjl;
    private androidx.viewpager2.widget.ViewPager2 vp2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zgz);
        initView();
        header.setText("找工作");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fragments.add(new ZGZ_A());
        fragments.add(new ZGZ_B());
        fragments.add(new ZGZ_C());
        viewPager2 = new ViewPager2(getSupportFragmentManager(), getLifecycle(), fragments);
        vp2.setUserInputEnabled(false);
        vp2.setAdapter(viewPager2);
        vp2.setCurrentItem(0);
        Text(zgz, tdjl, grjl);
        zgz.setOnClickListener(view -> {
            vp2.setCurrentItem(0, false);
            Text(zgz, tdjl, grjl);
        });
        tdjl.setOnClickListener(view -> {
            vp2.setCurrentItem(1, false);
            Text(tdjl, zgz, grjl);
        });
        grjl.setOnClickListener(view -> {
            vp2.setCurrentItem(2, false);
            Text(grjl, tdjl, zgz);
        });
    }

    //改变字体颜色
    public void Text(TextView t1, TextView t2, TextView t3) {
        t1.setTextColor(Color.parseColor("#002fa7"));
        t2.setTextColor(Color.parseColor("#000000"));
        t3.setTextColor(Color.parseColor("#000000"));
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        zgz = (TextView) findViewById(R.id.zgz);
        tdjl = (TextView) findViewById(R.id.tdjl);
        grjl = (TextView) findViewById(R.id.grjl);
        vp2 = findViewById(R.id.vp2);
    }
}
