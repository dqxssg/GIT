package com.example.myapplication7.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication7.R;
import com.example.myapplication7.adapter.Adapter_VP2;
import com.example.myapplication7.fragment.AA;
import com.example.myapplication7.fragment.BB;
import com.example.myapplication7.fragment.CC;
import com.example.myapplication7.fragment.DD;
import com.example.myapplication7.fragment.EE;
import com.example.myapplication7.util.App;
import com.example.myapplication7.util.HttpUtil;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Adapter_VP2 adapter_vp2;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", "JiEmony");
            jsonObject.put("password", "123456");
            new HttpUtil()
                    .sendResuilt("/prod-api/api/login", jsonObject.toString(), "POST", new Callback() {
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
                                    editor.commit();
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        initView();
        fragments.add(new AA());
        fragments.add(new BB());
        fragments.add(new CC());
        fragments.add(new DD());
        fragments.add(new EE());
        adapter_vp2 = new Adapter_VP2(getSupportFragmentManager(), getLifecycle(), fragments);
        vp2.setCurrentItem(0);
        TextColor(text1, text2, text3, text4, text5);
        ImageColor(image1, image2, image3, image4, image5);
        vp2.setUserInputEnabled(false);
        vp2.setAdapter(adapter_vp2);
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
            TextColor(text3, text1, text2, text4, text5);
            ImageColor(image3, image1, image2, image4, image5);
        });
        line4.setOnClickListener(v -> {
            vp2.setCurrentItem(3);
            TextColor(text4, text1, text2, text3, text5);
            ImageColor(image4, image1, image2, image3, image5);
        });
        line5.setOnClickListener(v -> {
            vp2.setCurrentItem(4);
            TextColor(text5, text1, text2, text3, text4);
            ImageColor(image5, image1, image2, image3, image4);

        });
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

    //字体颜色改变
    private static void ImageColor(ImageView i1, ImageView i2, ImageView i3, ImageView i4, ImageView i5) {
        i1.setColorFilter(Color.parseColor("#ffa500"));
        i2.setColorFilter(Color.parseColor("#000000"));
        i3.setColorFilter(Color.parseColor("#000000"));
        i4.setColorFilter(Color.parseColor("#000000"));
        i5.setColorFilter(Color.parseColor("#000000"));
    }

    //图片颜色改变
    private static void TextColor(TextView t1, TextView t2, TextView t3, TextView t4, TextView t5) {
        t1.setTextColor(Color.parseColor("#ffa500"));
        t2.setTextColor(Color.parseColor("#000000"));
        t3.setTextColor(Color.parseColor("#000000"));
        t4.setTextColor(Color.parseColor("#000000"));
        t5.setTextColor(Color.parseColor("#000000"));
    }

    //跳转全部服务页面
    public static void TZ_BB() {
        vp2.setCurrentItem(1);
        TextColor(text2, text1, text3, text4, text5);
        ImageColor(image2, image1, image3, image4, image5);
    }
}
