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
import com.example.zhcs.bean.S_WLCX_LV;
import com.example.zhcs.util.App;

import java.util.ArrayList;

public class Adapter_WLCX_LV extends ArrayAdapter<S_WLCX_LV> {
    public Adapter_WLCX_LV(@NonNull Context context, ArrayList<S_WLCX_LV> s_wlcx_lvs) {
        super(context, 0, s_wlcx_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_wlcx_lv, parent, false);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_WLCX_LV s_wlcx_lv = getItem(position);
        Glide.with(getContext())
                .load(App.url + s_wlcx_lv.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        viewHolder.text.setText(s_wlcx_lv.getName());
        return convertView;
    }

    class ViewHolder {
        TextView text;
        ImageView img;
    }
}
