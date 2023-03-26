package com.example.myapplication2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication2.R;
import com.example.myapplication2.bean.SJ_FY_LV;

import java.util.ArrayList;

public class Adapter_FY_LV extends ArrayAdapter<SJ_FY_LV> {
    public Adapter_FY_LV(@NonNull Context context, ArrayList<SJ_FY_LV> sj_fy_lvArrayList) {
        super(context, 0, sj_fy_lvArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodler viewHodler = new ViewHodler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fy_lv, parent, false);
            viewHodler.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        SJ_FY_LV sj_fy_lv = getItem(position);
        viewHodler.text.setText(sj_fy_lv.getName());
        return convertView;
    }

    class ViewHodler {
        TextView text;
    }
}
