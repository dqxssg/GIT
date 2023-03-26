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
import com.example.myapplication10.bean.S_TCJL_LV;
import com.example.myapplication10.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Adapter_TCJL_LV extends ArrayAdapter<S_TCJL_LV> {
    int i;

    public Adapter_TCJL_LV(@NonNull Context context, ArrayList<S_TCJL_LV> resource, int i) {
        super(context, 0, resource);
        this.i = i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tcjl_lv, parent, false);
            viewHolder.cph = convertView.findViewById(R.id.cph);
            viewHolder.sfje = convertView.findViewById(R.id.sfje);
            viewHolder.rcsj = convertView.findViewById(R.id.rcsj);
            viewHolder.ccsj = convertView.findViewById(R.id.ccsj);
            viewHolder.tccmc = convertView.findViewById(R.id.tccmc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_TCJL_LV s_tcjl_lv = getItem(position);
        if (position < i) {
            viewHolder.cph.setText("车牌号：" + s_tcjl_lv.getCarNum());
            viewHolder.sfje.setText("收费金额：" + s_tcjl_lv.getCharge());
            viewHolder.rcsj.setText("入场时间：" + s_tcjl_lv.getInTime());
            viewHolder.ccsj.setText("出场时间：" + s_tcjl_lv.getOutTime());
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("parkingid", s_tcjl_lv.getParkingid());
                ViewHolder finalViewHolder = viewHolder;
                new HttpUtil()
                        .sendResUtil("getParkInforById", jsonObject.toString(), "POST", new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                String s = response.body().string();
                                try {
                                    JSONObject jsonObject1 = new JSONObject(s);
                                    JSONArray jsonArray = jsonObject1.getJSONArray("ROWS_DETAIL");
                                    new Thread(() -> {
                                        try {
                                            finalViewHolder.tccmc.setText("停车场名称：" + jsonArray.getJSONObject(0).getString("parkName"));
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }
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
        return convertView;
    }

    private class ViewHolder {
        TextView cph;
        TextView sfje;
        TextView rcsj;
        TextView ccsj;
        TextView tccmc;
    }
}
