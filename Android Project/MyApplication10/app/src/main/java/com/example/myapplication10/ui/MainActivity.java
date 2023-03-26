package com.example.myapplication10.ui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication10.R;
import com.example.myapplication10.adapter.Adapter_VP2;
import com.example.myapplication10.fragment.A;
import com.example.myapplication10.fragment.B;
import com.example.myapplication10.fragment.C;
import com.example.myapplication10.fragment.D;
import com.example.myapplication10.fragment.E;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Adapter_VP2 adapter_vp2;
    private static ViewPager2 vp2;
    private static ImageView image1;
    private static ImageView image2;
    private static ImageView image3;
    private static ImageView image4;
    private static ImageView image5;
    private static TextView text1;
    private static TextView text2;
    private static TextView text3;
    private static TextView text4;
    private static TextView text5;

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
        TextColor(text1, text2, text3, text4, text5);
        ImageColor(image1, image2, image3, image4, image5);
        vp2.setUserInputEnabled(false);
        vp2.setAdapter(adapter_vp2);
        text1.setOnClickListener(v -> {
            vp2.setCurrentItem(0);
            TextColor(text1, text2, text3, text4, text5);
            ImageColor(image1, image2, image3, image4, image5);
        });
        image1.setOnClickListener(v -> {
            vp2.setCurrentItem(0);
            TextColor(text1, text2, text3, text4, text5);
            ImageColor(image1, image2, image3, image4, image5);
        });
        text2.setOnClickListener(v -> {
            vp2.setCurrentItem(1);
            TextColor(text2, text1, text3, text4, text5);
            ImageColor(image2, image1, image3, image4, image5);
        });
        image2.setOnClickListener(v -> {
            vp2.setCurrentItem(1);
            TextColor(text2, text1, text3, text4, text5);
            ImageColor(image2, image1, image3, image4, image5);
        });
        text3.setOnClickListener(v -> {
            vp2.setCurrentItem(2);
            TextColor(text3, text2, text1, text4, text5);
            ImageColor(image3, image2, image1, image4, image5);
        });
        image3.setOnClickListener(v -> {
            vp2.setCurrentItem(2);
            TextColor(text3, text2, text1, text4, text5);
            ImageColor(image3, image2, image1, image4, image5);
        });
        text4.setOnClickListener(v -> {
            vp2.setCurrentItem(3);
            TextColor(text4, text2, text3, text1, text5);
            ImageColor(image4, image2, image3, image1, image5);
        });
        image4.setOnClickListener(v -> {
            vp2.setCurrentItem(3);
            TextColor(text4, text2, text3, text1, text5);
            ImageColor(image4, image2, image3, image1, image5);
        });
        text5.setOnClickListener(v -> {
            vp2.setCurrentItem(4);
            TextColor(text5, text2, text3, text4, text1);
            ImageColor(image5, image2, image3, image4, image1);
        });
        image5.setOnClickListener(v -> {
            vp2.setCurrentItem(4);
            TextColor(text5, text2, text3, text4, text1);
            ImageColor(image5, image2, image3, image4, image1);
        });
    }

    private void initView() {
        vp2 = (ViewPager2) findViewById(R.id.vp2);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
    }

    private static void TextColor(TextView t1, TextView t2, TextView t3, TextView t4, TextView t5) {
        t1.setTextColor(Color.parseColor("#333333"));
        t2.setTextColor(Color.parseColor("#999999"));
        t3.setTextColor(Color.parseColor("#999999"));
        t4.setTextColor(Color.parseColor("#999999"));
        t5.setTextColor(Color.parseColor("#999999"));
    }

    private static void ImageColor(ImageView i1, ImageView i2, ImageView i3, ImageView i4, ImageView i5) {
        i1.setColorFilter(Color.parseColor("#333333"));
        i2.setColorFilter(Color.parseColor("#999999"));
        i3.setColorFilter(Color.parseColor("#999999"));
        i4.setColorFilter(Color.parseColor("#999999"));
        i5.setColorFilter(Color.parseColor("#999999"));
    }

    public static void TZ_B() {
        vp2.setCurrentItem(1);
        TextColor(text2, text1, text3, text4, text5);
        ImageColor(image2, image1, image3, image4, image5);
    }
}