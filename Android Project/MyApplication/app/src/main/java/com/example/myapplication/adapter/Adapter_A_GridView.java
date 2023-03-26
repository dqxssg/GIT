package com.example.myapplication.adapter;

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
import com.example.myapplication.R;
import com.example.myapplication.bean.ShuJv_A_GridView;
import com.example.myapplication.util.App;

import java.util.ArrayList;

public class Adapter_A_GridView extends ArrayAdapter<ShuJv_A_GridView> {
    public Adapter_A_GridView(@NonNull Context context, ArrayList<ShuJv_A_GridView> shuJv_a_listViews) {
        super(context, 0, shuJv_a_listViews);
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
        ShuJv_A_GridView shuJv_a_listView = getItem(position);
        if (position < 9) {
            Glide.with(getContext())
                    .load(App.url + shuJv_a_listView.getImgUrl())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(viewHolder.image);
            viewHolder.text.setText(shuJv_a_listView.getServiceName());
        } else if (position == 9) {
            viewHolder.image.setImageResource(R.drawable.gdfw);
            viewHolder.text.setText("更多服务");
        }
        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView text;
    }
}
