package com.example.myapplication2.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication2.R;
import com.example.myapplication2.adapter.Adapter_ViewPager2;
import com.example.myapplication2.fragment.A;
import com.example.myapplication2.fragment.B;
import com.example.myapplication2.fragment.C;
import com.example.myapplication2.fragment.D;
import com.example.myapplication2.fragment.E;
import com.example.myapplication2.fragment.LXQ_A;
import com.example.myapplication2.fragment.LXQ_B;
import com.example.myapplication2.util.App;
import com.example.myapplication2.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LXQ extends AppCompatActivity {
    private Adapter_ViewPager2 adapter_viewPager2;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private TextView back;
    private TextView header;
    private ImageView img;
    private TextView mc;
    private TextView flzc;
    private TextView zxrs;
    private TextView fwcs;
    private ViewPager2 vp2;
    private TextView f;
    private TextView y;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lxq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("律师详情");
        back.setOnClickListener(v -> {
            finish();
        });
        //添加界面
        TJJM(id);
        //选中
        XZ();
        //显示
        XS(id);
    }

    private void XS(int id) {
        new HttpUtil()
                .sendResultToken("/prod-api/api/lawyer-consultation/lawyer/" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            runOnUiThread(() -> {
                                try {
                                    Glide.with(LXQ.this)
                                            .load(App.url + jsonObject1.getString("avatarUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(img);
                                    mc.setText(jsonObject1.getString("name"));
                                    flzc.setText("法律专长；" + jsonObject1.getString("legalExpertiseName"));
                                    zxrs.setText("咨询人数；" + jsonObject1.getString("serviceTimes"));
                                    fwcs.setText("服务人数；" + jsonObject1.getString("serviceTimes"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void XZ() {
        f.setOnClickListener(v -> {
            vp2.setCurrentItem(0);
            textColor(f, y);
        });
        y.setOnClickListener(v -> {
            vp2.setCurrentItem(1);
            textColor(y, f);
        });
    }

    private void TJJM(int id) {
        fragments.add(new LXQ_A(id));
        fragments.add(new LXQ_B(id));
        adapter_viewPager2 = new Adapter_ViewPager2(getSupportFragmentManager(), getLifecycle(), fragments);
        vp2.setCurrentItem(0);
        vp2.setUserInputEnabled(false);
        vp2.setAdapter(adapter_viewPager2);
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        img = (ImageView) findViewById(R.id.img);
        mc = (TextView) findViewById(R.id.mc);
        flzc = (TextView) findViewById(R.id.flzc);
        zxrs = (TextView) findViewById(R.id.zxrs);
        fwcs = (TextView) findViewById(R.id.fwcs);
        vp2 = (ViewPager2) findViewById(R.id.vp2);
        f = (TextView) findViewById(R.id.f);
        y = (TextView) findViewById(R.id.y);
    }

    //字体选中颜色变化
    public void textColor(TextView t1, TextView t2) {
        t1.setTextColor(Color.parseColor("#002fa7"));
        t2.setTextColor(Color.parseColor("#000000"));
    }
}
