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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication7.R;
import com.example.myapplication7.bean.S_ZFZ_LV;
import com.example.myapplication7.util.App;

import java.util.ArrayList;

public class Adapter_ZFZ_LV extends ArrayAdapter<S_ZFZ_LV> {

    public Adapter_ZFZ_LV(@NonNull Context context, ArrayList<S_ZFZ_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zfz_lv, parent, false);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.wz = (TextView) convertView.findViewById(R.id.wz);
            viewHolder.mj = (TextView) convertView.findViewById(R.id.mj);
            viewHolder.jg = (TextView) convertView.findViewById(R.id.jg);
            viewHolder.jj = (TextView) convertView.findViewById(R.id.jj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_ZFZ_LV s_zfz_lv = getItem(position);
        LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zfz_lv, parent, false);
        Glide.with(getContext())
                .load(App.url + s_zfz_lv.getPic())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.wz.setText(s_zfz_lv.getAddress());
        viewHolder.mj.setText("房源面积：" + s_zfz_lv.getAreaSize()+"㎡");
        viewHolder.jg.setText("价格：" + s_zfz_lv.getPrice());
        viewHolder.jj.setText("简介：" + s_zfz_lv.getDescription());
        return convertView;
    }

    private class ViewHolder {
        private ImageView image;
        private TextView wz;
        private TextView mj;
        private TextView jg;
        private TextView jj;
    }
}
