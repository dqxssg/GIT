package com.example.myapplication2.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FWPJ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private EditText sr;
    private TextView xs;
    private RatingBar ratingbar;
    private TextView tj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fwpj);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        initView();
        header.setText("评价服务");
        back.setOnClickListener(v -> {
            finish();
        });
        sr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                xs.setText(s.length() + "/150字");
            }
        });
        tj.setOnClickListener(v -> {
            ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    String s = String.valueOf(rating);
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("Id", id);
                        jsonObject.put("evaluate", sr.getText().toString());
                        jsonObject.put("score", s);
                        new HttpUtil()
                                .sendResultToken("/prod-api/api/lawyer-consultation/legal-advice/evaluate", jsonObject.toString(), "POST", new Callback() {
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
                                                    Toast.makeText(FWPJ.this, "评价成功", Toast.LENGTH_SHORT).show();
                                                });
                                            } else {
                                                runOnUiThread(() -> {
                                                    try {
                                                        Toast.makeText(FWPJ.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
                }
            });
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        sr = (EditText) findViewById(R.id.sr);
        xs = (TextView) findViewById(R.id.xs);
        ratingbar = (RatingBar) findViewById(R.id.ratingbar);
        tj = (TextView) findViewById(R.id.tj);
    }
}
