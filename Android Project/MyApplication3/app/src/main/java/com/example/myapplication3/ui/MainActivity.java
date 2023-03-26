package com.example.myapplication3.ui;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication3.R;
import com.example.myapplication3.adapter.Adapter_ViewPager2;
import com.example.myapplication3.fragment.A;
import com.example.myapplication3.fragment.B;
import com.example.myapplication3.fragment.C;
import com.example.myapplication3.fragment.D;
import com.example.myapplication3.fragment.E;
import com.example.myapplication3.util.App;
import com.example.myapplication3.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Adapter_ViewPager2 adapter;

    private static ViewPager2 vp2;
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
        //登录
        DL();
        //VP2
        VP();
        //跳转
        TZ();
    }

    public static void TZ_B() {
        vp2.setCurrentItem(1);
        Text(text2, text1, text3, text4, text5);
        Image(img2, img1, img3, img4, img5);
    }

    private void TZ() {
        line1.setOnClickListener(v -> {
            vp2.setCurrentItem(0);
            Text(text1, text2, text3, text4, text5);
            Image(img1, img2, img3, img4, img5);
        });
        line2.setOnClickListener(v -> {
            vp2.setCurrentItem(1);
            Text(text2, text1, text3, text4, text5);
            Image(img2, img1, img3, img4, img5);
        });
        line3.setOnClickListener(v -> {
            vp2.setCurrentItem(2);
            Text(text3, text2, text1, text4, text5);
            Image(img3, img2, img1, img4, img5);
        });
        line4.setOnClickListener(v -> {
            vp2.setCurrentItem(3);
            Text(text4, text2, text3, text1, text5);
            Image(img4, img2, img3, img1, img5);
        });
        line5.setOnClickListener(v -> {
            vp2.setCurrentItem(4);
            Text(text5, text2, text3, text4, text1);
            Image(img5, img2, img3, img4, img1);
        });
    }

    private void VP() {
        fragments.add(new A());
        fragments.add(new B());
        fragments.add(new C());
        fragments.add(new D());
        fragments.add(new E());
        adapter = new Adapter_ViewPager2(getSupportFragmentManager(), getLifecycle(), fragments);
        vp2.setCurrentItem(0);
        Text(text1, text2, text3, text4, text5);
        Image(img1, img2, img3, img4, img5);
        vp2.setUserInputEnabled(false);
        vp2.setOffscreenPageLimit(fragments.size());
        vp2.setAdapter(adapter);
    }

    private void DL() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", "JiEmony");
            jsonObject.put("password", "123456");
            new HttpUtil()
                    .sendReslt("/prod-api/api/login", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                if (jsonObject1.getString("code").equals("200")) {
                                    SharedPreferences.Editor editor = App.sp.edit();
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

    private void initView() {
        vp2 = (ViewPager2) findViewById(R.id.vp2);
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

    public static void Image(ImageView img1, ImageView img2, ImageView img3, ImageView img4, ImageView img5) {
        img1.setColorFilter(Color.parseColor("#002fa7"));
        img2.setColorFilter(Color.parseColor("#000000"));
        img3.setColorFilter(Color.parseColor("#000000"));
        img4.setColorFilter(Color.parseColor("#000000"));
        img5.setColorFilter(Color.parseColor("#000000"));
    }

    public static void Text(TextView text1, TextView text2, TextView text3, TextView text4, TextView text5) {
        text1.setTextColor(Color.parseColor("#002fa7"));
        text2.setTextColor(Color.parseColor("#000000"));
        text3.setTextColor(Color.parseColor("#000000"));
        text4.setTextColor(Color.parseColor("#000000"));
        text5.setTextColor(Color.parseColor("#000000"));

    }
}