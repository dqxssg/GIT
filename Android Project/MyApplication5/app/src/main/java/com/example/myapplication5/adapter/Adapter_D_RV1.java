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
import com.example.myapplication5.bean.S_D_RV;
import com.example.myapplication5.util.App;

import java.util.ArrayList;

public class Adapter_D_RV1 extends ArrayAdapter<S_D_RV> {
    public Adapter_D_RV1(@NonNull Context context, ArrayList<S_D_RV> s_a_gvArrayList) {
        super(context, 0, s_a_gvArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_a_gv, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_D_RV s_a_gv = getItem(position);
        Glide.with(getContext())
                .load(App.url + s_a_gv.getImgUrl())
                .into(viewHolder.image);
        viewHolder.text.setText(s_a_gv.getName());
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView text;
    }
}
