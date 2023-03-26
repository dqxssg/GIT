package com.example.myapplication8.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication8.R;
import com.example.myapplication8.bean.S_A_GV;
import com.example.myapplication8.ui.B_FW;
import com.example.myapplication8.ui.DTCX;
import com.example.myapplication8.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Dialog_B extends DialogFragment {
    private String[] names = {"智慧医疗", "智能交通", "智慧环保", "智慧服务", "智慧养老", "智慧旅游"};
    private ArrayList<S_A_GV> s_a_gvArrayList = new ArrayList<>();
    private String name;
    private ImageView image;
    private TextView text;

    public Dialog_B(String name) {
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.adapter_a_gv, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        for (String s : names) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("serviceType", s);
                new HttpUtil()
                        .sendResuilt("getServiceByType", jsonObject.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                JSONObject jsonObject1 = null;
                                try {
                                    jsonObject1 = new JSONObject(s);
                                    s_a_gvArrayList = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_A_GV>>() {
                                    }.getType());
                                    for (S_A_GV s_a_gv : s_a_gvArrayList) {
                                        boolean matches = Pattern.matches(".*" + name + ".*", s_a_gv.getServiceName());
                                        if (matches) {
                                            getActivity().runOnUiThread(() -> {
                                                Glide.with(getActivity())
                                                        .load(s_a_gv.getIcon())
                                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                                        .into(image);
                                                text.setText(s_a_gv.getServiceName());
                                            });
                                            break;
                                        }
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
        image.setOnClickListener(v -> {
            if (Objects.equals(text.getText().toString(), "地铁查询")) {
                startActivity(new Intent(getActivity(), DTCX.class));
            } else {
                Intent intent = new Intent(getActivity(), B_FW.class);
                intent.putExtra("name", text.getText().toString());
                startActivity(intent);
            }
            dismiss();
        });
    }

    private void initView() {
        image = (ImageView) getView().findViewById(R.id.image);
        text = (TextView) getView().findViewById(R.id.text);
    }
}
