package com.example.myapplication5.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication5.R;
import com.example.myapplication5.adapter.Adapter_D_RV1;
import com.example.myapplication5.bean.S_D_RV;
import com.example.myapplication5.ui.MFZX;
import com.example.myapplication5.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Dialog_WTLX extends DialogFragment {
    private ArrayList<S_D_RV> s_d_rvArrayList = new ArrayList<>();
    private Adapter_D_RV1 adapter_d_rv1;
    private GridView gridview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flzc, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/legal-expertise/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_d_rvArrayList = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_D_RV>>() {
                            }.getType());
                            s_d_rvArrayList.sort(new Comparator<S_D_RV>() {
                                @Override
                                public int compare(S_D_RV o1, S_D_RV o2) {
                                    return o2.getId() - o1.getId();
                                }
                            });
                            adapter_d_rv1 = new Adapter_D_RV1(getActivity(), s_d_rvArrayList);
                            getActivity().runOnUiThread(() -> {
                                gridview.setAdapter(adapter_d_rv1);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    MFZX.XS(s_d_rvArrayList.get(position).getName());
                                    dismiss();
                                });
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void initView() {
        gridview = (GridView) getView().findViewById(R.id.gridview);
    }
}
