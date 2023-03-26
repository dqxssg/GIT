package com.example.myapplication.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JFJM extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private ImageView add;
    private LinearLayout line1;
    private LinearLayout line2;
    private LinearLayout line3;
    private TextView qzf;
    private TextView xs;
    private TextView yys1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jfjm);
        Bundle bundle = this.getIntent().getExtras();
        String yys = bundle.getString("yys");
        String sjh = bundle.getString("sjh");
        initView();
        header.setText("话费充值");
        yys1.setText(yys);
        back.setOnClickListener(v -> {
            finish();
        });
        line1.setOnClickListener(v -> {
            xs.setText("50");
        });
        line2.setOnClickListener(v -> {
            xs.setText("100");
        });
        line3.setOnClickListener(v -> {
            xs.setText("200");
        });
        qzf.setOnClickListener(v -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("paymentType", "电子支付");
                jsonObject.put("phonenumber", sjh);
                jsonObject.put("rechargeAmount", xs.getText().toString());
                jsonObject.put("type", "1");
                new HttpUtil()
                        .sendResyltToken("/prod-api/api/living/phone/recharge", jsonObject.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                try {
                                    JSONObject jsonObject1 = new JSONObject(s);
                                    if (jsonObject1.getString("code").equals("200")) {
                                        runOnUiThread(() -> {
                                            Toast.makeText(JFJM.this, "充值成功", Toast.LENGTH_SHORT).show();
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            try {
                                                Toast.makeText(JFJM.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
                                            } catch (JSONException e) {
                                                throw new RuntimeException(e);
                                            }
                                        });
                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        });
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
        add.setOnClickListener(v -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("yys", yys);
            editor.putString("sjh", sjh);
            editor.putString("sj", simpleDateFormat.format(date));
            editor.commit();
            runOnUiThread(() -> {
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            });
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        add = (ImageView) findViewById(R.id.add);
        line1 = (LinearLayout) findViewById(R.id.line1);
        line2 = (LinearLayout) findViewById(R.id.line2);
        line3 = (LinearLayout) findViewById(R.id.line3);
        qzf = (TextView) findViewById(R.id.qzf);
        xs = (TextView) findViewById(R.id.xs);
        yys1 = (TextView) findViewById(R.id.yys);
    }
}
