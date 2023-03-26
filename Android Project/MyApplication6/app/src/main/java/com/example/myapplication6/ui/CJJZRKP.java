package com.example.myapplication6.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication6.R;
import com.example.myapplication6.bean.S_JZRKP_LV;
import com.example.myapplication6.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CJJZRKP extends AppCompatActivity {
    private ArrayList<S_JZRKP_LV> s_jzrkp_lvs = new ArrayList<>();
    private TextView back;
    private TextView header;
    private EditText xm;
    private RadioGroup radiogroup;
    private RadioButton radiobotton1;
    private RadioButton radiobotton2;
    private EditText sfzh;
    private EditText csrq;
    private EditText sjh;
    private EditText dz;
    private TextView qd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cjjzrlp);
        Bundle bundle = this.getIntent().getExtras();
        String id = bundle.getString("id");
        initView();
        header.setText("创建就诊人卡片");
        back.setOnClickListener(v -> {
            finish();
        });
        if (Objects.equals(id, "wu")) {
            radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton radioButton = findViewById(checkedId);
                    qd.setOnClickListener(v -> {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("address", dz.getText().toString());
                            jsonObject.put("birthday", csrq.getText().toString());
                            jsonObject.put("cardId ", sfzh.getText().toString());
                            jsonObject.put("name", xm.getText().toString());
                            jsonObject.put("sex", radioButton.getAutofillValue().toString());
                            jsonObject.put("tel", sjh.getText().toString());
                            new HttpUtil()
                                    .sendResurltToken("/prod-api/api/hospital/patient", jsonObject.toString(), "POST", new Callback() {
                                        @Override
                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                        }

                                        @Override
                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                            String s = response.body().string();
                                            try {
                                                JSONObject jsonObject1 = new JSONObject(s);
                                                if (jsonObject1.getJSONArray("code").equals("200")) {
                                                    runOnUiThread(() -> {
                                                        Toast.makeText(CJJZRKP.this, "添加成功", Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    });
                                                } else {
                                                    runOnUiThread(() -> {
                                                        try {
                                                            Toast.makeText(CJJZRKP.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
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
                }
            });
        } else {
            new HttpUtil().sendResurltToken("/prod-api/api/hospital/patient/list", "", "GET", new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String s = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        s_jzrkp_lvs = new Gson().fromJson(jsonObject.getString("rows"), new TypeToken<List<S_JZRKP_LV>>() {
                        }.getType());
                        for (S_JZRKP_LV s_jzrkp_lv : s_jzrkp_lvs) {
                            if (Objects.equals(s_jzrkp_lv.getId(), id)) {
                                xm.setText(s_jzrkp_lv.getName());
                                if (Objects.equals(s_jzrkp_lv.getSex(), "0")) {
                                    radiobotton1.setChecked(true);
                                } else {
                                    radiobotton2.setChecked(true);
                                }
                                sfzh.setText(s_jzrkp_lv.getCardId() + "");
                                csrq.setText(s_jzrkp_lv.getBirthday() + "");
                                sjh.setText(s_jzrkp_lv.getTel());
                                dz.setText(s_jzrkp_lv.getAddress());
                                radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                        RadioButton radioButton = findViewById(checkedId);
                                        qd.setOnClickListener(v -> {
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("address", dz.getText().toString());
                                                jsonObject.put("birthday", csrq.getText().toString());
                                                jsonObject.put("cardId ", sfzh.getText().toString());
                                                jsonObject.put("name", xm.getText().toString());
                                                jsonObject.put("sex", radioButton.getAutofillValue().toString());
                                                jsonObject.put("tel", sjh.getText().toString());
                                                new HttpUtil()
                                                        .sendResurltToken("/prod-api/api/hospital/patient", jsonObject.toString(), "PUT", new Callback() {
                                                            @Override
                                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                                            }

                                                            @Override
                                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                                String s = response.body().string();
                                                                try {
                                                                    JSONObject jsonObject1 = new JSONObject(s);
                                                                    if (jsonObject1.getJSONArray("code").equals("200")) {
                                                                        runOnUiThread(() -> {
                                                                            Toast.makeText(CJJZRKP.this, "添加成功", Toast.LENGTH_SHORT).show();
                                                                            finish();
                                                                        });
                                                                    } else {
                                                                        runOnUiThread(() -> {
                                                                            try {
                                                                                Toast.makeText(CJJZRKP.this, jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
                                                                            } catch (
                                                                                    JSONException e) {
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
                                    }
                                });
                                break;
                            }
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        xm = (EditText) findViewById(R.id.xm);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        radiobotton1 = (RadioButton) findViewById(R.id.radiobotton1);
        radiobotton2 = (RadioButton) findViewById(R.id.radiobotton2);
        sfzh = (EditText) findViewById(R.id.sfzh);
        csrq = (EditText) findViewById(R.id.csrq);
        sjh = (EditText) findViewById(R.id.sjh);
        dz = (EditText) findViewById(R.id.dz);
        qd = (TextView) findViewById(R.id.qd);
    }
}
