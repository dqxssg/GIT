package com.example.test.adapter;

import android.content.Context;
import android.text.Html;
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
import com.example.test.R;
import com.example.test.bean.ShuJv_A_ListView;
import com.example.test.util.App;

import java.util.ArrayList;

public class A_ListView extends ArrayAdapter<ShuJv_A_ListView> {
    public A_ListView(@NonNull Context context, ArrayList<ShuJv_A_ListView> shuJv_a_listViews) {
        super(context, 0, shuJv_a_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_listview, parent, false);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.text1 = convertView.findViewById(R.id.text1);
            viewHolder.text2 = convertView.findViewById(R.id.text2);
            viewHolder.text3 = convertView.findViewById(R.id.text3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_A_ListView shuJv_a_listView = getItem(position);
        viewHolder.text3.setText("阅读：" + shuJv_a_listView.getReadNum());
        viewHolder.text2.setText(Html.fromHtml(shuJv_a_listView.getContent()));
        viewHolder.text1.setText(shuJv_a_listView.getTitle());
        Glide.with(getContext())
                .load(App.url + shuJv_a_listView.getCover())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView text1;
        TextView text2;
        TextView text3;
    }
}
