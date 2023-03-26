package com.example.test.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class YJFK extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private EditText sr;
    private TextView tj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yjfk);
        initView();
        header.setText("意见反馈");
        back.setOnClickListener(view -> {
            finish();
        });
        tj.setOnClickListener(view -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("content", sr.getText());
                new HttpUtil()
                        .sendResulToken("/prod-api/api/common/feedback", jsonObject.toString(), "POST", new Callback() {
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
                                            sr.setText("");
                                            Toast.makeText(YJFK.this, "提交成功", Toast.LENGTH_SHORT).show();
                                        });
                                    } else {
                                        runOnUiThread(() -> {
                                            Toast.makeText(YJFK.this, "提交失败", Toast.LENGTH_SHORT).show();
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
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        sr = (EditText) findViewById(R.id.sr);
        tj = (TextView) findViewById(R.id.tj);
    }
}
