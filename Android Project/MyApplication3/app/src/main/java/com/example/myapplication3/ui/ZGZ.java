package com.example.myapplication3.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication3.R;
import com.example.myapplication3.adapter.Adapter_ViewPager2;
import com.example.myapplication3.fragment.ZGZ_A;
import com.example.myapplication3.fragment.ZGZ_B;
import com.example.myapplication3.fragment.ZGZ_C;

import java.util.ArrayList;

public class ZGZ extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Adapter_ViewPager2 adapter;
    private ImageView back;
    private TextView header;
    private TextView zgz;
    private TextView tdjl;
    private TextView grjl;
    private ViewPager2 vp2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zgz);
        initView();
        //添加界面
        TJJM();
        //点击
        DJ();
    }

    private void DJ() {
        zgz.setOnClickListener(v -> {
            header.setText("找工作");
            vp2.setCurrentItem(0);
            Text(zgz, tdjl, grjl);
        });
        tdjl.setOnClickListener(v -> {
            header.setText("投递记录");
            vp2.setCurrentItem(1);
            Text(tdjl, zgz, grjl);
        });
        grjl.setOnClickListener(v -> {
            header.setText("个人简历");
            vp2.setCurrentItem(2);
            Text(grjl, tdjl, zgz);
        });
    }

    private void TJJM() {
        fragments.add(new ZGZ_A());
        fragments.add(new ZGZ_B());
        fragments.add(new ZGZ_C());
        adapter = new Adapter_ViewPager2(getSupportFragmentManager(), getLifecycle(), fragments);
        vp2.setUserInputEnabled(false);
        header.setText("找工作");
        vp2.setCurrentItem(0);
        Text(zgz, tdjl, grjl);
        vp2.setAdapter((RecyclerView.Adapter) adapter);
    }


    //字体颜色变换
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
        vp2 = (ViewPager2) findViewById(R.id.vp2);
    }
}
