package com.example.myapplication10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication10.R;
import com.example.myapplication10.bean.S_DF_LV;
import com.example.myapplication10.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Adapter_DF_LV extends ArrayAdapter<S_DF_LV> {
    public Adapter_DF_LV(@NonNull Context context, ArrayList<S_DF_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHOdler viewHOdler = new ViewHOdler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_df_lv, parent, false);
            viewHOdler.jfhh = convertView.findViewById(R.id.jfhh);
            viewHOdler.jfdw = convertView.findViewById(R.id.jfdw);
            viewHOdler.hz = convertView.findViewById(R.id.hz);
            viewHOdler.dzxx = convertView.findViewById(R.id.dzxx);
            viewHOdler.yv = convertView.findViewById(R.id.yv);
            viewHOdler.qf = convertView.findViewById(R.id.qf);
            convertView.setTag(viewHOdler);
        } else {
            viewHOdler = (ViewHOdler) convertView.getTag();
        }
        S_DF_LV s_df_lv = getItem(position);
        viewHOdler.jfhh.setText("缴费单位：" + s_df_lv.getUnit());
        viewHOdler.jfdw.setText("缴费户号：" + s_df_lv.getAccountId());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userid", "2");
            ViewHOdler finalViewHOdler = viewHOdler;
            new HttpUtil()
                    .sendResUtil("getUserInfo", jsonObject.toString(), "POST", new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String s = response.body().string();
                            try {
                                JSONObject jsonObject1 = new JSONObject(s);
                                JSONArray jsonArray = jsonObject1.getJSONArray("ROWS_DETAIL");
                                finalViewHOdler.hz.setText("户名：" + jsonArray.getJSONObject(0).getString("name"));
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        viewHOdler.dzxx.setText("地址信息：" + s_df_lv.getAccountAddress());
        viewHOdler.yv.setText("当前可用余额：" + s_df_lv.getCost());
        viewHOdler.qf.setText("当前欠费余额：" + s_df_lv.getBanlance());
        return convertView;
    }

    private class ViewHOdler {
        TextView jfhh;
        TextView jfdw;
        TextView hz;
        TextView dzxx;
        TextView yv;
        TextView qf;
    }
}
