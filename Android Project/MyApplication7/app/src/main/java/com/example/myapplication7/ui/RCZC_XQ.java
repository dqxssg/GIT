package com.example.myapplication7.ui;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication7.R;
import com.example.myapplication7.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RCZC_XQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView bt;
    private TextView nr;
    private TextView rq;
    private TextView fbz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rczc_xq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("政策详情");
        back.setOnClickListener(v -> {
            finish();
        });
        new HttpUtil()
                .sendResuiltToken("/prod-api/api/youth-inn/talent-policy/"+id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            runOnUiThread(() -> {
                                try {
                                    bt.setText(jsonObject1.getString("title"));
                                    nr.setText(Html.fromHtml(jsonObject1.getString("content")));
                                    rq.setText(jsonObject1.getString("createTime"));
                                    fbz.setText(jsonObject1.getString("author"));
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

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        bt = (TextView) findViewById(R.id.bt);
        nr = (TextView) findViewById(R.id.nr);
        rq = (TextView) findViewById(R.id.rq);
        fbz = (TextView) findViewById(R.id.fbz);
    }
}
