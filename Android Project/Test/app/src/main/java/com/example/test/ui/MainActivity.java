package com.example.test.ui;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.test.R;
import com.example.test.adapter.ViewPager2;
import com.example.test.bean.ShuJv_A_ListView;
import com.example.test.fragment.A;
import com.example.test.fragment.B;
import com.example.test.fragment.C;
import com.example.test.fragment.D;
import com.example.test.fragment.E;
import com.example.test.util.App;
import com.example.test.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ViewPager2 viewPager2Adapter;

    private static androidx.viewpager2.widget.ViewPager2 vp2;
    private LinearLayout line1;
    private static ImageView img1;
    private static TextView text1;
    private LinearLayout line2;
    private static ImageView img2;
    private static TextView text2;
    private LinearLayout line3;
    private static ImageView img3;
    private static TextView text3;
    private LinearLayout line4;
    private static ImageView img4;
    private static TextView text4;
    private LinearLayout line5;
    private static ImageView img5;
    private static TextView text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //ViewPager2
        VP2();
        //点击切换界面
        DJQHJM();
        //登录
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", "JiEmony");
            jsonObject.put("password", "123456");
            new HttpUtil()
                    .sendResult("/prod-api/api/login", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                if (jsonObject1.getString("code").equals("200")) {
                                    SharedPreferences.Editor editor = App.sharedPreferences.edit();
                                    editor.putString("token", jsonObject1.getString("token"));
                                    editor.apply();
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    //跳转到B()界面
    public static void TZ_B() {
        vp2.setCurrentItem(1);
        Image(img2, img1, img3, img4, img5);
        Text(text2, text1, text3, text4, text5);
    }

    //点击切换界面
    private void DJQHJM() {
        line1.setOnClickListener(view -> {
            vp2.setCurrentItem(0);
            Image(img1, img2, img3, img4, img5);
            Text(text1, text2, text3, text4, text5);
        });
        line2.setOnClickListener(view -> {
            vp2.setCurrentItem(1);
            Image(img2, img1, img3, img4, img5);
            Text(text2, text1, text3, text4, text5);
        });
        line3.setOnClickListener(view -> {
            vp2.setCurrentItem(2);
            Image(img3, img2, img1, img4, img5);
            Text(text3, text2, text1, text4, text5);
        });
        line4.setOnClickListener(view -> {
            vp2.setCurrentItem(3);
            Image(img4, img2, img3, img1, img5);
            Text(text4, text2, text3, text1, text5);
        });
        line5.setOnClickListener(view -> {
            vp2.setCurrentItem(4);
            Image(img5, img2, img3, img4, img1);
            Text(text5, text2, text3, text4, text1);
        });
    }

    //ViewPager2
    private void VP2() {
        fragments.add(new A());
        fragments.add(new B());
        fragments.add(new C());
        fragments.add(new D());
        fragments.add(new E());
        viewPager2Adapter = new ViewPager2(getSupportFragmentManager(), getLifecycle(), fragments);
        vp2.setCurrentItem(0);
        Image(img1, img2, img3, img4, img5);
        Text(text1, text2, text3, text4, text5);
        vp2.setUserInputEnabled(false);
        vp2.setOffscreenPageLimit(fragments.size());
        vp2.setAdapter(viewPager2Adapter);
    }

    //图片变化
    public static void Image(ImageView img1, ImageView img2, ImageView img3, ImageView img4, ImageView img5) {
        img1.setColorFilter(Color.parseColor("#002fa7"));
        img2.setColorFilter(Color.parseColor("#000000"));
        img3.setColorFilter(Color.parseColor("#000000"));
        img4.setColorFilter(Color.parseColor("#000000"));
        img5.setColorFilter(Color.parseColor("#000000"));

    }

    //文字变化
    public static void Text(TextView text1, TextView text2, TextView text3, TextView text4, TextView text5) {
        text1.setTextColor(Color.parseColor("#002fa7"));
        text2.setTextColor(Color.parseColor("#000000"));
        text3.setTextColor(Color.parseColor("#000000"));
        text4.setTextColor(Color.parseColor("#000000"));
        text5.setTextColor(Color.parseColor("#000000"));
    }

    private void initView() {
        vp2 = (androidx.viewpager2.widget.ViewPager2) findViewById(R.id.vp2);
        line1 = (LinearLayout) findViewById(R.id.line1);
        img1 = (ImageView) findViewById(R.id.img1);
        text1 = (TextView) findViewById(R.id.text1);
        line2 = (LinearLayout) findViewById(R.id.line2);
        img2 = (ImageView) findViewById(R.id.img2);
        text2 = (TextView) findViewById(R.id.text2);
        line3 = (LinearLayout) findViewById(R.id.line3);
        img3 = (ImageView) findViewById(R.id.img3);
        text3 = (TextView) findViewById(R.id.text3);
        line4 = (LinearLayout) findViewById(R.id.line4);
        img4 = (ImageView) findViewById(R.id.img4);
        text4 = (TextView) findViewById(R.id.text4);
        line5 = (LinearLayout) findViewById(R.id.line5);
        img5 = (ImageView) findViewById(R.id.img5);
        text5 = (TextView) findViewById(R.id.text5);
    }
}