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
import com.example.myapplication7.bean.S_ZFFWRX_LV;

import java.util.ArrayList;

public class Adapter_ZFFWRX_LV extends ArrayAdapter<S_ZFFWRX_LV> {
    public Adapter_ZFFWRX_LV(@NonNull Context context, ArrayList<S_ZFFWRX_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zffwrx_lv, parent, false);
            viewHolder.bt = convertView.findViewById(R.id.bt);
            viewHolder.cbdw = convertView.findViewById(R.id.cbdw);
            viewHolder.tjsj = convertView.findViewById(R.id.tjsj);
            viewHolder.zt = convertView.findViewById(R.id.zt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_ZFFWRX_LV s_zffwrx_lv = getItem(position);
        viewHolder.bt.setText(s_zffwrx_lv.getContent());
        viewHolder.cbdw.setText(s_zffwrx_lv.getUndertaker());
        viewHolder.tjsj.setText(s_zffwrx_lv.getCreateTime());
        if (s_zffwrx_lv.getState().equals("1")) {
            viewHolder.zt.setText("已处理");
        } else {
            viewHolder.zt.setText("未处理");
        }
        return convertView;
    }

    private class ViewHolder {
        TextView bt;
        TextView cbdw;
        TextView tjsj;
        TextView zt;
    }
}
