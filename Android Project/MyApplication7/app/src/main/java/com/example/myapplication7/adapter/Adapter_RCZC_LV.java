package com.example.myapplication7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication7.R;
import com.example.myapplication7.bean.S_RCZC_LV;

import java.util.ArrayList;

public class Adapter_RCZC_LV extends ArrayAdapter<S_RCZC_LV> {
    public Adapter_RCZC_LV(@NonNull Context context, ArrayList<S_RCZC_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rczc_lv, parent, false);
            viewHolder.bt = convertView.findViewById(R.id.bt);
            viewHolder.sj = convertView.findViewById(R.id.sj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_RCZC_LV s_rczc_lv = getItem(position);
        viewHolder.bt.setText(s_rczc_lv.getTitle());
        viewHolder.sj.setText(s_rczc_lv.getCreateTime());
        return convertView;
    }

    private class ViewHolder {
        TextView bt;
        TextView sj;
    }
}
