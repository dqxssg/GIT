package com.example.myapplication7.adapter;

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
import com.example.myapplication7.R;
import com.example.myapplication7.bean.S_AA_GV;
import com.example.myapplication7.util.App;

import java.util.ArrayList;

public class Adapter_AA_GV extends ArrayAdapter<S_AA_GV> {
    public Adapter_AA_GV(@NonNull Context context, ArrayList<S_AA_GV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_aa_gv, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_AA_GV s_aa_gv = getItem(position);
        if (position < 9) {
            Glide.with(getContext())
                    .load(App.url + s_aa_gv.getImgUrl())
                    .into(viewHolder.image);
            viewHolder.text.setText(s_aa_gv.getServiceName());
        } else if (position == 9) {
            viewHolder.image.setImageResource(R.drawable.gdfw);
            viewHolder.text.setText("跟多服务");
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView text;
    }
}
