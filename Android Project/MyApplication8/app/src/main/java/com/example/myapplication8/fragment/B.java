package com.example.myapplication8.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication8.R;
import com.example.myapplication8.adapter.Adapter_V_GV;
import com.example.myapplication8.bean.S_A_GV;
import com.example.myapplication8.dialog.Dialog_B;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class B extends Fragment {
    private ArrayList<S_A_GV> s_a_gvArrayList = new ArrayList<>();
    private Adapter_V_GV adapter_v_gv;
    private TextView header;
    private EditText ss;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private TextView t6;
    private GridView gridview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.b, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        header.setText("全部服务");
        GV(t1.getText().toString());
        t1.setOnClickListener(v -> {
            GV(t1.getText().toString());
        });
        t2.setOnClickListener(v -> {
            GV(t2.getText().toString());
        });
        t3.setOnClickListener(v -> {
            GV(t3.getText().toString());
        });
        t4.setOnClickListener(v -> {
            GV(t4.getText().toString());
        });
        t5.setOnClickListener(v -> {
            GV(t5.getText().toString());
        });
        t6.setOnClickListener(v -> {
            GV(t6.getText().toString());
        });
        ss.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Dialog_B dialog_b = new Dialog_B(ss.getText().toString());
                transaction.add(dialog_b, "dialog-tag");
                transaction.show(dialog_b);
                transaction.commit();
                return true;
            }
            return false;
        });
    }

    private void GV(String name) {
        s_a_gvArrayList.clear();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("serviceType", name);
            new HttpUtil()
                    .sendResuilt("getServiceByType", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                s_a_gvArrayList = new Gson().fromJson(jsonObject1.getJSONArray("ROWS_DETAIL").toString(), new TypeToken<List<S_A_GV>>() {
                                }.getType());
                                adapter_v_gv = new Adapter_V_GV(getActivity(), s_a_gvArrayList);
                                getActivity().runOnUiThread(() -> {
                                    gridview.setAdapter(adapter_v_gv);
                                    gridview.setOnItemClickListener((parent, view, position, id) -> {
                                        switch (s_a_gvArrayList.get(position).getServiceName()) {
                                            case "地铁查询":
                                                startActivity(new Intent(getActivity(), DTCX.class));
                                                break;
                                            default:
                                                Intent intent = new Intent(getActivity(), B_FW.class);
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
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void initView() {
        header = (TextView) getView().findViewById(R.id.header);
        ss = (EditText) getView().findViewById(R.id.ss);
        t1 = (TextView) getView().findViewById(R.id.t1);
        t2 = (TextView) getView().findViewById(R.id.t2);
        t3 = (TextView) getView().findViewById(R.id.t3);
        t4 = (TextView) getView().findViewById(R.id.t4);
        t5 = (TextView) getView().findViewById(R.id.t5);
        t6 = (TextView) getView().findViewById(R.id.t6);
        gridview = (GridView) getView().findViewById(R.id.gridview);
    }
}
