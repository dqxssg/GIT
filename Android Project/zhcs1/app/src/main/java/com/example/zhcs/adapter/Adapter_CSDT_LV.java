package com.example.zhcs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zhcs.R;
import com.example.zhcs.bean.S_CSDT_LV;

import java.util.ArrayList;

public class Adapter_CSDT_LV extends ArrayAdapter<S_CSDT_LV> {
    public Adapter_CSDT_LV(@NonNull Context context, ArrayList<S_CSDT_LV> s_csdt_lvArrayAdapter) {
        super(context, 0, s_csdt_lvArrayAdapter);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_csdt_lv, parent, false);
            viewHolder.lxmc = convertView.findViewById(R.id.lxmc);
            viewHolder.xyzmc = convertView.findViewById(R.id.xyzmc);
            viewHolder.ddbzsc = convertView.findViewById(R.id.ddbzsc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_CSDT_LV s_csdt_lv = getItem(position);
        viewHolder.lxmc.setText(s_csdt_lv.getLineName());
        viewHolder.xyzmc.setText("下一站：" + s_csdt_lv.getNextStep().getName());
        viewHolder.ddbzsc.setText("到达本站时间：" + s_csdt_lv.getReachTime() + "分钟");
        return convertView;
    }

    private class ViewHolder {
        TextView lxmc;
        TextView xyzmc;
        TextView ddbzsc;
    }
}
