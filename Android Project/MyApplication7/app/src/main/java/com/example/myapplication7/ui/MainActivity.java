package com.example.myapplication7.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication7.R;
import com.example.myapplication7.adapter.Adapter_VP2;
import com.example.myapplication7.fragment.A;
import com.example.myapplication7.fragment.B;
import com.example.myapplication7.fragment.C;
import com.example.myapplication7.fragment.D;
import com.example.myapplication7.fragment.E;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Adapter_VP2 adapter_vp2;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ViewPager2 vp2;
    private ImageView t1;
    private ImageView t2;
    private ImageView t3;
    private ImageView t4;
    private ImageView t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragments.add(new A());
        fragments.add(new B());
        fragments.add(new C());
        fragments.add(new D());
        fragments.add(new E());
        adapter_vp2 = new Adapter_VP2(getSupportFragmentManager(), getLifecycle(), fragments);
        vp2.setCurrentItem(0);
        vp2.setAdapter(adapter_vp2);
        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        TextColor(t1, t2, t3, t4, t5);
                        break;
                    case 1:
                        TextColor(t2, t1, t3, t4, t5);
                        break;
                    case 2:
                        TextColor(t3, t2, t1, t4, t5);
                        break;
                    case 3:
                        TextColor(t4, t2, t3, t1, t5);
                        break;
                    case 4:
                        TextColor(t5, t2, t3, t4, t1);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void initView() {
        vp2 = (ViewPager2) findViewById(R.id.vp2);
        t1 = (ImageView) findViewById(R.id.t1);
        t2 = (ImageView) findViewById(R.id.t2);
        t3 = (ImageView) findViewById(R.id.t3);
        t4 = (ImageView) findViewById(R.id.t4);
        t5 = (ImageView) findViewById(R.id.t5);
    }

    //滑动颜色变化
    private void TextColor(ImageView t1, ImageView t2, ImageView t3, ImageView t4, ImageView t5) {
        t1.setColorFilter(Color.parseColor("#000000"));
        t2.setColorFilter(Color.parseColor("#f0f0f0"));
        t3.setColorFilter(Color.parseColor("#f0f0f0"));
        t4.setColorFilter(Color.parseColor("#f0f0f0"));
        t5.setColorFilter(Color.parseColor("#f0f0f0"));
    }
}