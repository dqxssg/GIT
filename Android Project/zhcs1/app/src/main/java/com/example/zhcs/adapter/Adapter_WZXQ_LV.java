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
import com.example.zhcs.bean.S_WZXQ_LV;

import java.util.ArrayList;

public class Adapter_WZXQ_LV extends ArrayAdapter<S_WZXQ_LV> {
    public Adapter_WZXQ_LV(@NonNull Context context, ArrayList<S_WZXQ_LV> s_wzxq_lvs) {
        super(context, 0,s_wzxq_lvs );
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_wzxq_lv, parent, false);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_WZXQ_LV s_wzxq_lv = getItem(position);
        viewHolder.text.setText("问："+s_wzxq_lv.getContent());
        return convertView;
    }

    class ViewHolder {
        TextView text;
    }
}
