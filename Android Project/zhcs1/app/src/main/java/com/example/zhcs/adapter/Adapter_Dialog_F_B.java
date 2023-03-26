package com.example.zhcs.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhcs.R;
import com.example.zhcs.bean.S_A_GV;
import com.example.zhcs.ui.CSDT;
import com.example.zhcs.ui.CWYY;
import com.example.zhcs.ui.FW;
import com.example.zhcs.ui.WLCX;
import com.example.zhcs.util.App;
import com.example.zhcs.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Adapter_Dialog_F_B extends DialogFragment {
    private ArrayList<S_A_GV> s_a_gvArrayList = new ArrayList<>();
    private ArrayList<S_A_GV> s_a_gvArrayList1 = new ArrayList<>();
    private Adapter_B_GV adapter_b_gv;
    private String name;
    private LinearLayout line;
    private ImageView image;
    private TextView title;

    public Adapter_Dialog_F_B(String name) {
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.adapter_dialog_f_b, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        new HttpUtil().sendResultToken("/prod-api/api/service/list", "", "GET", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    s_a_gvArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_A_GV>>() {
                    }.getType());
                    for (S_A_GV s_a_gv : s_a_gvArrayList) {
                        boolean matches = Pattern.matches(".*" + name + ".*", s_a_gv.getServiceName());
                        if (matches) {
                            s_a_gvArrayList1.add(s_a_gv);
                            getActivity().runOnUiThread(() -> {
                                Glide.with(getContext()).load(App.url + s_a_gvArrayList1.get(0).getImgUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(image);
                                title.setText(s_a_gvArrayList1.get(0).getServiceName());
                            });
                            break;
                        }
                        getActivity().runOnUiThread(() -> {
                            image.setImageResource(R.drawable.gdfw);
                            title.setText("暂无该服务");
                        });
                    }
                    getActivity().runOnUiThread(() -> {
                        line.setOnClickListener(v -> {
                            switch (title.getText().toString()) {
                                case "宠物医院":
                                    startActivity(new Intent(getActivity(), CWYY.class));
                                    break;
                                case "城市地铁":
                                    startActivity(new Intent(getActivity(), CSDT.class));
                                    break;
                                case "物流查询":
                                    startActivity(new Intent(getActivity(), WLCX.class));
                                    break;
                                case "暂无该服务":
                                    dismiss();
                                    break;
                                default:
                                    Intent intent = new Intent(getActivity(), FW.class);
                                    intent.putExtra("name", title.getText().toString());
                                    startActivity(intent);
                                    break;
                            }
                        });
                    });
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void initView() {
        line = (LinearLayout) getView().findViewById(R.id.line);
        image = (ImageView) getView().findViewById(R.id.image);
        title = (TextView) getView().findViewById(R.id.title);
    }
}
