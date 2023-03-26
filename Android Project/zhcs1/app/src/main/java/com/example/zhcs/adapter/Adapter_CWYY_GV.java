package com.example.zhcs.adapter;

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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhcs.R;
import com.example.zhcs.bean.S_CWYY_GV;
import com.example.zhcs.util.App;

import java.util.ArrayList;

public class Adapter_CWYY_GV extends ArrayAdapter<S_CWYY_GV> {
    public Adapter_CWYY_GV(@NonNull Context context, ArrayList<S_CWYY_GV> s_cwyy_gvs) {
        super(context, 0, s_cwyy_gvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cwyy_gv, parent, false);
            viewHolder.text = convertView.findViewById(R.id.text);
            viewHolder.img = convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_CWYY_GV s_cwyy_gv = getItem(position);
        viewHolder.text.setText(s_cwyy_gv.getName());
        Glide.with(getContext())
                .load(App.url + s_cwyy_gv.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView text;
    }
}
