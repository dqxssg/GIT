package com.example.myapplication9.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication9.R;
import com.example.myapplication9.bean.S_DDLB_LV;

import java.util.ArrayList;

public class Adapter_DDLB_LV extends ArrayAdapter<S_DDLB_LV> {
    public Adapter_DDLB_LV(@NonNull Context context, ArrayList<S_DDLB_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodler viewHodler = new ViewHodler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ddlb_lv, parent, false);
            viewHodler.date = convertView.findViewById(R.id.date);
            viewHodler.type = convertView.findViewById(R.id.type);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        S_DDLB_LV s_ddlb_lv = getItem(position);
        viewHodler.date.setText(s_ddlb_lv.getDate());
        viewHodler.type.setText(s_ddlb_lv.getType());
        return convertView;
    }

    private class ViewHodler {
        TextView type;
        TextView date;
    }
}

