package com.example.myapplication5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.myapplication5.R;
import com.example.myapplication5.bean.S_A_GV;
import com.example.myapplication5.bean.S_B_SS_GV1;
import com.example.myapplication5.util.App;

import java.util.ArrayList;

public class Adapter_B_SS_GV1 extends ArrayAdapter<S_B_SS_GV1> {
    public Adapter_B_SS_GV1(@NonNull Context context, ArrayList<S_B_SS_GV1> s) {
        super(context, 0, s);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_b_ss_gv1, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_B_SS_GV1 s_b_ss_gv1 = getItem(position);
        Glide.with(getContext())
                .load(App.url + s_b_ss_gv1.getImgUrl())
                .into(viewHolder.image);
        viewHolder.text.setText(s_b_ss_gv1.getName());
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView text;
    }
}
