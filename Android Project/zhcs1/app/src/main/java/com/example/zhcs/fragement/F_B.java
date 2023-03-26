package com.example.zhcs.fragement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.zhcs.R;
import com.example.zhcs.adapter.Adapter_A_GV;
import com.example.zhcs.adapter.Adapter_B_GV;
import com.example.zhcs.adapter.Adapter_Dialog_F_B;
import com.example.zhcs.bean.S_A_GV;
import com.example.zhcs.ui.CSDT;
import com.example.zhcs.ui.CWYY;
import com.example.zhcs.ui.FW;
import com.example.zhcs.ui.MainActivity;
import com.example.zhcs.ui.WLCX;
import com.example.zhcs.util.HttpUtil;
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

public class F_B extends Fragment {
    private ArrayList<S_A_GV> s_a_gvArrayList = new ArrayList<>();
    private ArrayList<S_A_GV> s_a_gvArrayList1 = new ArrayList<>();
    private Adapter_B_GV adapter_b_gv;
    private TextView header;
    private EditText ss;
    private TextView bmfw;
    private TextView czfw;
    private TextView shfw;
    private GridView gridview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_b, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        header.setText("全部服务");
        //点击切换类别
        DJQHLB();
        //gridview
        GV(bmfw.getText().toString());
        textColor(bmfw, czfw, shfw);
        //搜索
        SS();
    }

    private void SS() {
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                FragmentManager manager = getChildFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Adapter_Dialog_F_B dialog = new Adapter_Dialog_F_B(ss.getText().toString());
                transaction.add(dialog, "dialog-tag");
                transaction.show(dialog);
                transaction.commit();
                return true;
            }
            return false;
        });
    }

    private void GV(String name) {
        s_a_gvArrayList.clear();
        s_a_gvArrayList1.clear();
        new HttpUtil()
                .sendResultToken("/prod-api/api/service/list", "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            s_a_gvArrayList1 = new Gson().fromJson(jsonObject.getJSONArray("rows").toString(), new TypeToken<List<S_A_GV>>() {
                            }.getType());
                            for (S_A_GV s_a_gv : s_a_gvArrayList1) {
                                if (name.equals(s_a_gv.getServiceType())) {
                                    s_a_gvArrayList.add(s_a_gv);
                                }
                            }
                            adapter_b_gv = new Adapter_B_GV(getActivity(), s_a_gvArrayList);
                            getActivity().runOnUiThread(() -> {
                                gridview.setAdapter(adapter_b_gv);
                                gridview.setOnItemClickListener((parent, view, position, id) -> {
                                    switch (s_a_gvArrayList.get(position).getServiceName()) {
                                        case "宠物医院":
                                            startActivity(new Intent(getActivity(), CWYY.class));
                                            break;
                                        case "城市地铁":
                                            startActivity(new Intent(getActivity(), CSDT.class));
                                            break;
                                        case "物流查询":
                                            startActivity(new Intent(getActivity(), WLCX.class));
                                            break;
                                        default:
                                            Intent intent = new Intent(getActivity(), FW.class);
                                            intent.putExtra("name", s_a_gvArrayList.get(position).getServiceName());
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

    private void DJQHLB() {
        bmfw.setOnClickListener(v -> {
            textColor(bmfw, czfw, shfw);
            GV(bmfw.getText().toString());
        });
        czfw.setOnClickListener(v -> {
            textColor(czfw, bmfw, shfw);
            GV(czfw.getText().toString());
        });
        shfw.setOnClickListener(v -> {
            textColor(shfw, bmfw, czfw);
            GV(shfw.getText().toString());
        });
    }

    private void initView() {
        header = (TextView) getView().findViewById(R.id.header);
        ss = (EditText) getView().findViewById(R.id.ss);
        bmfw = (TextView) getView().findViewById(R.id.bmfw);
        czfw = (TextView) getView().findViewById(R.id.czfw);
        shfw = (TextView) getView().findViewById(R.id.shfw);
        gridview = (GridView) getView().findViewById(R.id.gridview);
    }

    //字体颜色变化
    private void textColor(TextView t1, TextView t2, TextView t3) {
        t1.setTextColor(Color.parseColor("#002fa7"));
        t2.setTextColor(Color.parseColor("#000000"));
        t3.setTextColor(Color.parseColor("#000000"));
    }
}
