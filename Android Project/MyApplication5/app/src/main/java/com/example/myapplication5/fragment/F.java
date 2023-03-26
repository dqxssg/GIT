package com.example.myapplication5.fragment;

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
import com.example.myapplication5.R;
import com.example.myapplication5.ui.XS_XQ;
import com.example.myapplication5.util.App;
import com.example.myapplication5.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class F extends Fragment {
    private int id;
    private TextView jybj;
    private TextView cynx;
    private TextView zyzh;
    private TextView grjj;
    private ImageView image;

    public F(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
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
                            getActivity().runOnUiThread(() -> {
                                try {
                                    Glide.with(getActivity())
                                            .load(App.url + jsonObject1.getString("certificateImgUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(image);
                                    jybj.setText("教育背景："+jsonObject1.getString("eduInfo"));
                                    cynx.setText("从业年限："+jsonObject1.getString("workStartAt"));
                                    zyzh.setText("执业证号："+jsonObject1.getString("licenseNo"));
                                    grjj.setText("个人简介："+jsonObject1.getString("baseInfo"));
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
        jybj = (TextView) getView().findViewById(R.id.jybj);
        cynx = (TextView) getView().findViewById(R.id.cynx);
        zyzh = (TextView) getView().findViewById(R.id.zyzh);
        grjj = (TextView) getView().findViewById(R.id.grjj);
        image = (ImageView) getView().findViewById(R.id.image);
    }
}
