package com.example.myapplication8.adapter;

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
import com.example.myapplication8.R;
import com.example.myapplication8.bean.S_A_GV;

import java.util.ArrayList;

public class Adapter_A_GV extends ArrayAdapter<S_A_GV> {
    public Adapter_A_GV(@NonNull Context context, ArrayList<S_A_GV> resource) {
        super(context, 0, resource);
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
        S_A_GV s_a_gv = getItem(position);
        if (position < 9) {
            Glide.with(getContext())
                    .load(s_a_gv.getIcon())
                    .into(viewHolder.image);
            viewHolder.text.setText(s_a_gv.getServiceName());
        } else if (position == 9) {
            viewHolder.image.setImageResource(R.drawable.gdfw);
            viewHolder.text.setText("更多服务");
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView text;
    }
}
