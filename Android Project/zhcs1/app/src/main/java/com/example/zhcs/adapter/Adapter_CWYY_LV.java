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
import com.example.zhcs.bean.S_CWYY_LV;
import com.example.zhcs.util.App;

import java.util.ArrayList;

public class Adapter_CWYY_LV extends ArrayAdapter<S_CWYY_LV> {
    public Adapter_CWYY_LV(@NonNull Context context, ArrayList<S_CWYY_LV> s_cwyy_lvArrayList) {
        super(context, 0, s_cwyy_lvArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cwyy_lv, parent, false);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.xm = convertView.findViewById(R.id.xm);
            viewHolder.ms = convertView.findViewById(R.id.ms);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_CWYY_LV s_cwyy_lv = getItem(position);
        viewHolder.xm.setText(s_cwyy_lv.getDoctor().getName());
        viewHolder.ms.setText("案例描述："+s_cwyy_lv.getQuestion());
        Glide.with(getContext())
                .load(App.url + s_cwyy_lv.getDoctor().getAvatar())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView xm;
        TextView ms;
    }
}
