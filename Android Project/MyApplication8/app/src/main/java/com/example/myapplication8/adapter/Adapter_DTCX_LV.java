package com.example.myapplication8.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication8.R;
import com.example.myapplication8.bean.S_DTCX_LV;

import java.util.ArrayList;

public class Adapter_DTCX_LV extends ArrayAdapter<S_DTCX_LV> {
    public Adapter_DTCX_LV(@NonNull Context context, ArrayList<S_DTCX_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodler viewHodler = new ViewHodler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dtcx_lv, parent, false);
            viewHodler.lxmc = convertView.findViewById(R.id.lxmc);
            viewHodler.xyz = convertView.findViewById(R.id.xyz);
            viewHodler.sj = convertView.findViewById(R.id.sj);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        S_DTCX_LV s_dtcx_lv = getItem(position);
        viewHodler.lxmc.setText(s_dtcx_lv.getName());
        viewHodler.xyz.setText("下一站" + s_dtcx_lv.getNextname());
        viewHodler.sj.setText("预计需要" + s_dtcx_lv.getTime() + "分钟");
        return convertView;
    }

    private class ViewHodler {
        TextView lxmc;
        TextView xyz;
        TextView sj;
    }
}
