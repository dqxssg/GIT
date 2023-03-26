package com.example.myapplication2.ui;

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

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Adapter_ViewPager2;
import com.example.myapplication2.fragment.A;
import com.example.myapplication2.fragment.B;
import com.example.myapplication2.fragment.C;
import com.example.myapplication2.fragment.D;
import com.example.myapplication2.fragment.E;
import com.example.myapplication2.util.App;
import com.example.myapplication2.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Adapter_ViewPager2 adapter_viewPager2;
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
    private static ViewPager2 vp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //Login
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
        //添加界面
        AddFragment();
        //跳转
        TZ();
    }

    private void TZ() {
        line1.setOnClickListener(v -> {
            vp2.setCurrentItem(0);
            text(text1, text2, text3, text4, text5);
            image(image1, image2, image3, image4, image5);
        });
        line2.setOnClickListener(v -> {
            vp2.setCurrentItem(1);
            text(text2, text1, text3, text4, text5);
            image(image2, image1, image3, image4, image5);
        });
        line3.setOnClickListener(v -> {
            vp2.setCurrentItem(2);
            text(text3, text2, text1, text4, text5);
            image(image3, image2, image1, image4, image5);
        });
        line4.setOnClickListener(v -> {
            vp2.setCurrentItem(3);
            text(text4, text2, text3, text1, text5);
            image(image4, image2, image3, image1, image5);
        });
        line5.setOnClickListener(v -> {
            vp2.setCurrentItem(4);
            text(text5, text2, text3, text4, text1);
            image(image5, image2, image3, image4, image1);
        });
    }

    private void AddFragment() {
        fragments.add(new A());
        fragments.add(new B());
        fragments.add(new C());
        fragments.add(new D());
        fragments.add(new E());
        adapter_viewPager2 = new Adapter_ViewPager2(getSupportFragmentManager(), getLifecycle(), fragments);
        vp2.setCurrentItem(0);
        text(text1, text2, text3, text4, text5);
        image(image1, image2, image3, image4, image5);
        vp2.setUserInputEnabled(false);
        vp2.setAdapter(adapter_viewPager2);
    }

    private void initView() {
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
        vp2 = (ViewPager2) findViewById(R.id.vp2);
    }

    //改变字体颜色
    public static void text(TextView textView1, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        textView1.setTextColor(Color.parseColor("#002fa7"));
        textView2.setTextColor(Color.parseColor("#000000"));
        textView3.setTextColor(Color.parseColor("#000000"));
        textView4.setTextColor(Color.parseColor("#000000"));
        textView5.setTextColor(Color.parseColor("#000000"));
    }

    //改变图片颜色
    public static void image(ImageView imageView1, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5) {
        imageView1.setColorFilter(Color.parseColor("#002fa7"));
        imageView2.setColorFilter(Color.parseColor("#000000"));
        imageView3.setColorFilter(Color.parseColor("#000000"));
        imageView4.setColorFilter(Color.parseColor("#000000"));
        imageView5.setColorFilter(Color.parseColor("#000000"));
    }

    //跳转F_B
    public static void TZF_B() {
        vp2.setCurrentItem(1);
        text(text2, text1, text3, text4, text5);
        image(image2, image1, image3, image4, image5);
    }
}