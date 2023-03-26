package com.example.myapplication5.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
import com.example.myapplication5.R;
import com.example.myapplication5.adapter.Adapter_VP2;
import com.example.myapplication5.fragment.F;
import com.example.myapplication5.fragment.G;
import com.example.myapplication5.util.App;
import com.example.myapplication5.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XS_XQ extends AppCompatActivity {
    private Adapter_VP2 adapter_vp2;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private TextView back;
    private TextView header;
    private ImageView image;
    private TextView mc;
    private TextView flzc;
    private TextView zxrs;
    private TextView fwcs;
    private TextView fwfs;
    private TextView yhpj;
    private ViewPager2 vp2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ls_xq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("律师详情");
        back.setOnClickListener(v -> {
            finish();
        });
        //添加界面
        TJJM(id);
        //点击事件
        fwfs.setOnClickListener(v -> {
            vp2.setCurrentItem(0);
            TextColor(fwfs, yhpj);
        });
        yhpj.setOnClickListener(v -> {
            vp2.setCurrentItem(1);
            TextColor(yhpj, fwfs);
        });
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/lawyer/" + id, "", "GET", new Callback() {
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
                                    Glide.with(XS_XQ.this)
                                            .load(App.url + jsonObject1.getString("avatarUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image);
                                    mc.setText(jsonObject1.getString("name"));
                                    flzc.setText("法律专长：" + jsonObject1.getString("legalExpertiseName"));
                                    zxrs.setText("咨询人数：" + jsonObject1.getString("serviceTimes"));
                                    fwcs.setText("服务次数：" + jsonObject1.getString("serviceTimes"));
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

    private void TJJM(int id) {
        fragments.add(new F(id));
        fragments.add(new G(id));
        adapter_vp2 = new Adapter_VP2(getSupportFragmentManager(), getLifecycle(), fragments);
        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                System.out.println("123:"+position);
                switch (position) {
                    case 0:
                        TextColor(fwfs, yhpj);
                        break;
                    case 1:
                        TextColor(yhpj, fwfs);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        vp2.setCurrentItem(0);
        TextColor(fwfs, yhpj);
        vp2.setAdapter(adapter_vp2);
    }

    //切换改变字体颜色
    private void TextColor(TextView t1, TextView t2) {
        t1.setTextColor(Color.parseColor("#002fa7"));
        t2.setTextColor(Color.parseColor("#000000"));
    }


    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        image = (ImageView) findViewById(R.id.image);
        mc = (TextView) findViewById(R.id.mc);
        flzc = (TextView) findViewById(R.id.flzc);
        zxrs = (TextView) findViewById(R.id.zxrs);
        fwcs = (TextView) findViewById(R.id.fwcs);
        fwfs = (TextView) findViewById(R.id.fwfs);
        yhpj = (TextView) findViewById(R.id.yhpj);
        vp2 = (ViewPager2) findViewById(R.id.vp2);
    }
}
