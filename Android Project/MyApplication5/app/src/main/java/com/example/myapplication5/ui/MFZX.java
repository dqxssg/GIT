package com.example.myapplication5.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication5.R;
import com.example.myapplication5.bean.S_D_RV;
import com.example.myapplication5.fragment.Dialog_FLZC;
import com.example.myapplication5.fragment.Dialog_WTLX;
import com.example.myapplication5.util.App;
import com.example.myapplication5.util.HttpUtil;
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

public class MFZX extends AppCompatActivity {
    private ArrayList<S_D_RV> s_d_rvArrayList = new ArrayList<>();
    private TextView back;
    private TextView header;
    private ImageView img;
    private TextView mc;
    private TextView flzc;
    private TextView zxrs;
    private TextView fwcs;
    private static TextView wtlx;
    private EditText wtms;
    private EditText lxdh;
    private TextView tj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mfzx);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("咨询");
        back.setOnClickListener(v -> {
            finish();
        });
        wtlx.setOnClickListener(v -> {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            Dialog_WTLX dialog_flzc = new Dialog_WTLX();
            transaction.add(dialog_flzc, "dialog-tag");
            transaction.show(dialog_flzc);
            transaction.commit();
        });
        TJ(id);
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
                                    Glide.with(MFZX.this)
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


    public static void XS(String name) {
        wtlx.setText(name);
    }


    private void TJ(int id) {
        tj.setOnClickListener(v -> {
            new HttpUtil()
                    .sendResurltToken("/prod-api/api/lawyer-consultation/legal-expertise/list", "", "GET", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(s);
                                s_d_rvArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_D_RV>>() {
                                }.getType());
                                for (S_D_RV s_d_rv : s_d_rvArrayList) {
                                    if (s_d_rv.getName().equals(wtlx.getText().toString())) {
                                        JSONObject jsonObject0 = new JSONObject();
                                        try {
                                            jsonObject0.put("lawyerId", id);
                                            jsonObject0.put("legalExpertiseId", s_d_rv.getId());
                                            System.out.println("123"+s_d_rv.getName());
                                            System.out.println("456"+s_d_rv.getId());
                                            jsonObject0.put("content", wtms.getText().toString());
                                            jsonObject0.put("phone", lxdh.getText().toString());
                                            new HttpUtil()
                                                    .sendResurltToken("/prod-api/api/lawyer-consultation/legal-advice", jsonObject0.toString(), "POST", new Callback() {
                                                        @Override
                                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                        }

                                                        @Override
                                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                            String s1=response.body().string();
                                                            try {
                                                                JSONObject jsonObject1 = new JSONObject(s1);
                                                                System.out.println("789:"+s1);
                                                                if (jsonObject1.getString("code").equals("200")) {
                                                                    runOnUiThread(() -> {
                                                                        Toast.makeText(MFZX.this, "提交成功", Toast.LENGTH_SHORT).show();
                                                                        finish();
                                                                    });
                                                                } else {
                                                                    runOnUiThread(() -> {
                                                                        try {
                                                                            Toast.makeText(MFZX.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
                                        break;
                                    }
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        img = (ImageView) findViewById(R.id.img);
        mc = (TextView) findViewById(R.id.mc);
        flzc = (TextView) findViewById(R.id.flzc);
        zxrs = (TextView) findViewById(R.id.zxrs);
        fwcs = (TextView) findViewById(R.id.fwcs);
        wtlx = (TextView) findViewById(R.id.wtlx);
        wtms = (EditText) findViewById(R.id.wtms);
        lxdh = (EditText) findViewById(R.id.lxdh);
        tj = (TextView) findViewById(R.id.tj);
    }
}
