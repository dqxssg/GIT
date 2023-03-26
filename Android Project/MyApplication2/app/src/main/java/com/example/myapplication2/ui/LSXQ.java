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
import com.example.myapplication2.fragment.LSXQ_A;
import com.example.myapplication2.fragment.LSXQ_B;
import com.example.myapplication2.util.App;
import com.example.myapplication2.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LSXQ extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Adapter_ViewPager2 adapter_viewPager2;
    private TextView back;
    private TextView header;
    private ImageView image;
    private TextView mc;
    private TextView zc;
    private TextView zxcs;
    private TextView fwzs;
    private ViewPager2 vp2;
    private TextView fwfs;
    private TextView yhpj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lsxq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        back.setOnClickListener(v -> {
            finish();
        });
        header.setText("律师详情");
        //添加界面
        TJJM(id);
        //点击切换
        DJQH();
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
                                    Glide.with(LSXQ.this)
                                            .load(App.url + jsonObject1.getString("avatarUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image);
                                    mc.setText(jsonObject1.getString("name"));
                                    zc.setText("法律专长：" + jsonObject1.getString("legalExpertiseName"));
                                    zxcs.setText("咨询人数：" + jsonObject1.getString("serviceTimes"));
                                    fwzs.setText("服务人数：" + jsonObject1.getString("serviceTimes"));
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

    private void DJQH() {
        fwfs.setOnClickListener(v -> {
            text(fwfs, yhpj);
            vp2.setCurrentItem(0);
        });
        yhpj.setOnClickListener(v -> {
            text(yhpj, fwfs);
            vp2.setCurrentItem(1);
        });
    }

    private void TJJM(int id) {
        fragments.add(new LSXQ_A(id));
        fragments.add(new LSXQ_B(id));
        adapter_viewPager2 = new Adapter_ViewPager2(getSupportFragmentManager(), getLifecycle(), fragments);
        text(fwfs, yhpj);
        vp2.setCurrentItem(0);
        vp2.setUserInputEnabled(false);
        vp2.setAdapter(adapter_viewPager2);
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        image = (ImageView) findViewById(R.id.image);
        mc = (TextView) findViewById(R.id.mc);
        zc = (TextView) findViewById(R.id.zc);
        zxcs = (TextView) findViewById(R.id.zxcs);
        fwzs = (TextView) findViewById(R.id.fwzs);
        vp2 = (ViewPager2) findViewById(R.id.vp2);
        fwfs = (TextView) findViewById(R.id.fwfs);
        yhpj = (TextView) findViewById(R.id.yhpj);
    }

    //改变字体颜色
    public void text(TextView t1, TextView t2) {
        t1.setTextColor(Color.parseColor("#002fa7"));
        t2.setTextColor(Color.parseColor("#000000"));
    }
}
