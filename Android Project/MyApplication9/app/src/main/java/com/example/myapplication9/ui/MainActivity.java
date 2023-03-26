package com.example.myapplication9.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication9.R;
import com.example.myapplication9.adapter.ViewPager2Adapter;
import com.example.myapplication9.fragment.A;
import com.example.myapplication9.fragment.B;
import com.example.myapplication9.fragment.C;
import com.example.myapplication9.fragment.D;
import com.example.myapplication9.fragment.E;
import com.example.myapplication9.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ViewPager2Adapter viewPager2Adapter;
    private static ViewPager2 vp2;
    private LinearLayout line1;
    private static ImageView image1;
    private static TextView text1;
    private LinearLayout line2;
    private static ImageView image2;
    private static TextView text2;
    private LinearLayout line3;
    private static ImageView image3;
    private static TextView text3;
    private LinearLayout line4;
    private static ImageView image4;
    private static TextView text4;
    private LinearLayout line5;
    private static ImageView image5;
    private static TextView text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //添加界面
        TJJM();
        //点击切换界面
        DJQHJM();
    }

    private void DJQHJM() {
        line1.setOnClickListener(v -> {
            vp2.setCurrentItem(0);
            TextColor(text1, text2, text3, text4, text5);
            ImageColor(image1, image2, image3, image4, image5);
        });
        line2.setOnClickListener(v -> {
            vp2.setCurrentItem(1);
            TextColor(text2, text1, text3, text4, text5);
            ImageColor(image2, image1, image3, image4, image5);
        });
        line3.setOnClickListener(v -> {
            vp2.setCurrentItem(2);
            TextColor(text3, text2, text1, text4, text5);
            ImageColor(image3, image2, image1, image4, image5);
        });
        line4.setOnClickListener(v -> {
            vp2.setCurrentItem(3);
            TextColor(text4, text2, text3, text1, text5);
            ImageColor(image4, image2, image3, image1, image5);
        });
        line5.setOnClickListener(v -> {
            vp2.setCurrentItem(4);
            TextColor(text5, text2, text3, text4, text1);
            ImageColor(image5, image2, image3, image4, image1);
        });
    }

    private void TJJM() {
        fragments.add(new A());
        fragments.add(new B());
        fragments.add(new C());
        fragments.add(new D());
        fragments.add(new E());
        viewPager2Adapter = new ViewPager2Adapter(getSupportFragmentManager(), getLifecycle(), fragments);
        vp2.setUserInputEnabled(false);
        vp2.setCurrentItem(0);
        TextColor(text1, text2, text3, text4, text5);
        ImageColor(image1, image2, image3, image4, image5);
        vp2.setAdapter(viewPager2Adapter);
    }

    private void initView() {
        vp2 = (ViewPager2) findViewById(R.id.vp2);
        line1 = (LinearLayout) findViewById(R.id.line1);
        image1 = (ImageView) findViewById(R.id.image1);
        text1 = (TextView) findViewById(R.id.text1);
        line2 = (LinearLayout) findViewById(R.id.line2);
        image2 = (ImageView) findViewById(R.id.image2);
        text2 = (TextView) findViewById(R.id.text2);
        line3 = (LinearLayout) findViewById(R.id.line3);
        image3 = (ImageView) findViewById(R.id.image3);
        text3 = (TextView) findViewById(R.id.text3);
        line4 = (LinearLayout) findViewById(R.id.line4);
        image4 = (ImageView) findViewById(R.id.image4);
        text4 = (TextView) findViewById(R.id.text4);
        line5 = (LinearLayout) findViewById(R.id.line5);
        image5 = (ImageView) findViewById(R.id.image5);
        text5 = (TextView) findViewById(R.id.text5);
    }

    //图片改变颜色
    private static void ImageColor(ImageView i1, ImageView i2, ImageView i3, ImageView i4, ImageView i5) {
        i1.setColorFilter(Color.parseColor("#333333"));
        i2.setColorFilter(Color.parseColor("#999999"));
        i3.setColorFilter(Color.parseColor("#999999"));
        i4.setColorFilter(Color.parseColor("#999999"));
        i5.setColorFilter(Color.parseColor("#999999"));
    }

    //字体改变颜色
    private static void TextColor(TextView t1, TextView t2, TextView t3, TextView t4, TextView t5) {
        t1.setTextColor(Color.parseColor("#333333"));
        t2.setTextColor(Color.parseColor("#666666"));
        t3.setTextColor(Color.parseColor("#666666"));
        t4.setTextColor(Color.parseColor("#666666"));
        t5.setTextColor(Color.parseColor("#666666"));
    }

    //跳转B()界面
    public static void TZ_B() {
        vp2.setCurrentItem(1);
        TextColor(text2, text1, text3, text4, text5);
        ImageColor(image2, image1, image3, image4, image5);
    }
}