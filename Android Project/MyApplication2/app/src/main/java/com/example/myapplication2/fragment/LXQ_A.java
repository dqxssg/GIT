package com.example.myapplication2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication2.R;
import com.example.myapplication2.util.App;
import com.example.myapplication2.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LXQ_A extends Fragment {
    private int id;
    private TextView zybh;
    private TextView cynx;
    private TextView lxjj;
    private TextView jybj;
    private ImageView img;

    public LXQ_A(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lxq_a, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
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
                            getActivity().runOnUiThread(() -> {
                                try {
                                    Glide.with(getActivity())
                                            .load(App.url + jsonObject1.getString("certificateImgUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(img);
                                    zybh.setText("职业编号：" + jsonObject1.getString("licenseNo"));
                                    cynx.setText("从业年限：" + jsonObject1.getString("workStartAt") + "--至今");
                                    lxjj.setText("律师简介：" + jsonObject1.getString("baseInfo"));
                                    jybj.setText("教育背景：" + jsonObject1.getString("eduInfo"));
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
        zybh = (TextView) getView().findViewById(R.id.zybh);
        cynx = (TextView) getView().findViewById(R.id.cynx);
        lxjj = (TextView) getView().findViewById(R.id.lxjj);
        jybj = (TextView) getView().findViewById(R.id.jybj);
        img = (ImageView) getView().findViewById(R.id.img);
    }
}
