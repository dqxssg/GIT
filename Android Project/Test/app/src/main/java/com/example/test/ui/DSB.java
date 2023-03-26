package com.example.test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.bean.ShuJv_ZHBS_ZD;
import com.example.test.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DSB extends AppCompatActivity {
    private ArrayList<ShuJv_ZHBS_ZD> shuJv_zhbs_zds = new ArrayList<>();
    private ImageView back;
    private TextView header;
    private TextView xyb;
    private TextView fh;
    private TextView qd;
    private TextView zd;
    private EditText ckxm;
    private EditText sjhm;
    private EditText scdd;
    private EditText xcdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dsb);
        Bundle bundle = this.getIntent().getExtras();
        int i = bundle.getInt("i");
        int id = bundle.getInt("id");
        String datee = bundle.getString("datee");
        String timee = bundle.getString("timee");
        initView();
        header.setText("第三步");
        back.setOnClickListener(view -> {
            finish();
        });
        fh.setOnClickListener(view -> {
            finish();
        });
        new HttpUtil()
                .sendResulToken("/prod-api/api/bus/stop/list?linesId=" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            shuJv_zhbs_zds = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<ShuJv_ZHBS_ZD>>() {
                            }.getType());
                            runOnUiThread(() -> {
                                qd.setText(shuJv_zhbs_zds.get(0).getName());
                                zd.setText(shuJv_zhbs_zds.get(shuJv_zhbs_zds.size() - 1).getName());
                                xyb.setOnClickListener(view -> {
                                    Intent intent = new Intent(DSB.this, DSIB.class);
                                    intent.putExtra("ckxm", ckxm.getText().toString());
                                    intent.putExtra("sjhm", sjhm.getText().toString());
                                    intent.putExtra("scdd", scdd.getText().toString());
                                    intent.putExtra("xcdd", xcdd.getText().toString());
                                    intent.putExtra("i", i);
                                    intent.putExtra("id", id);
                                    intent.putExtra("datee", datee);
                                    intent.putExtra("timee", timee);
                                    startActivity(intent);
                                });
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        xyb = (TextView) findViewById(R.id.xyb);
        fh = (TextView) findViewById(R.id.fh);
        qd = (TextView) findViewById(R.id.qd);
        zd = (TextView) findViewById(R.id.zd);
        ckxm = (EditText) findViewById(R.id.ckxm);
        sjhm = (EditText) findViewById(R.id.sjhm);
        scdd = (EditText) findViewById(R.id.scdd);
        xcdd = (EditText) findViewById(R.id.xcdd);
    }
}
