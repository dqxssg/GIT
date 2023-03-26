package com.example.myapplication2.adapter;

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
import com.example.myapplication2.R;
import com.example.myapplication2.bean.ShuJv_A_GridView;
import com.example.myapplication2.bean.ShuJv_LV_GridView;
import com.example.myapplication2.bean.ShuJv_LV_ListView2;
import com.example.myapplication2.util.App;

import java.util.ArrayList;

public class Adapter_LV_GridView   extends ArrayAdapter<ShuJv_LV_GridView> {
        public Adapter_LV_GridView(@NonNull Context context, ArrayList<ShuJv_LV_GridView> shuJv_lv_gridViews) {
            super(context, 0, shuJv_lv_gridViews);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder = new ViewHolder();
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_a_gridview, parent, false);
                viewHolder.image = convertView.findViewById(R.id.image);
                viewHolder.text = convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ShuJv_LV_GridView  shuJv_lv_gridView = getItem(position);
                Glide.with(getContext())
                        .load(App.url + shuJv_lv_gridView.getImgUrl())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                        .into(viewHolder.image);
                viewHolder.text.setText(shuJv_lv_gridView.getName());
            return convertView;
        }

        class ViewHolder {
            ImageView image;
            TextView text;
        }
    }
