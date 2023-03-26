package com.example.myapplication5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication5.R;
import com.example.myapplication5.bean.S_D_LV1;
import com.example.myapplication5.util.App;

import java.util.ArrayList;

public class Adapter_D_LV1 extends ArrayAdapter<S_D_LV1> {
    public Adapter_D_LV1(@NonNull Context context, ArrayList<S_D_LV1> s) {
        super(context, 0, s);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodler viewHodler = new ViewHodler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_d_lv1, parent, false);
            viewHodler.image = convertView.findViewById(R.id.image);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        S_D_LV1 s_d_lv1 = getItem(position);
        Glide.with(getContext())
                .load(App.url + s_d_lv1.getImageUrls())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHodler.image);
        return convertView;
    }

    private class ViewHodler {
        ImageView image;
    }
}
