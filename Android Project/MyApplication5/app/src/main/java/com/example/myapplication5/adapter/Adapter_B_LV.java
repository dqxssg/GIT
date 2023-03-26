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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication5.R;
import com.example.myapplication5.bean.S_B_LV;
import com.example.myapplication5.util.App;

import java.util.ArrayList;

public class Adapter_B_LV extends ArrayAdapter<S_B_LV> {
    public Adapter_B_LV(@NonNull Context context, ArrayList<S_B_LV> s_b_lvArrayList) {
        super(context, 0, s_b_lvArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodler viewHodler = new ViewHodler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_b_lv, parent, false);
            viewHodler.bt = convertView.findViewById(R.id.bt);
            viewHodler.sj = convertView.findViewById(R.id.sj);
            viewHodler.image = convertView.findViewById(R.id.image);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        S_B_LV s_b_lv = getItem(position);
        viewHodler.bt.setText(s_b_lv.getTitle());
        viewHodler.sj.setText(s_b_lv.getCreateTime());
        Glide.with(getContext())
                .load(App.url + s_b_lv.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHodler.image);
        return convertView;
    }

    private class ViewHodler {
        TextView bt;
        TextView sj;
        ImageView image;
    }
}
