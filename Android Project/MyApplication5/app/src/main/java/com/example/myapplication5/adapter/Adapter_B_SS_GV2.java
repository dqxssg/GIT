package com.example.myapplication5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication5.R;
import com.example.myapplication5.bean.S_B_SS_GV1;
import com.example.myapplication5.bean.S_B_SS_GV2;

import java.util.ArrayList;

public class Adapter_B_SS_GV2 extends ArrayAdapter<S_B_SS_GV2> {

    public Adapter_B_SS_GV2(@NonNull Context context, ArrayList<S_B_SS_GV2> s) {
        super(context, 0, s);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_b_ss_gv2, parent, false);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_B_SS_GV2 s_b_ss_gv2 = getItem(position);
        viewHolder.text.setText(s_b_ss_gv2.getKeyword());
        return convertView;
    }

    private class ViewHolder {
        TextView text;
    }
}
