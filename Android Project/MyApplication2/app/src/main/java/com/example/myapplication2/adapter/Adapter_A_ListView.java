package com.example.myapplication2.adapter;

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
import com.example.myapplication2.R;
import com.example.myapplication2.bean.ShuJv_A_ListView;
import com.example.myapplication2.util.App;

import java.util.ArrayList;

public class Adapter_A_ListView extends ArrayAdapter<ShuJv_A_ListView> {
    public Adapter_A_ListView(@NonNull Context context, ArrayList<ShuJv_A_ListView> shuJv_a_listViews) {
        super(context, 0, shuJv_a_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_a_listview, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.bt = convertView.findViewById(R.id.bt);
            viewHolder.nr = convertView.findViewById(R.id.nr);
            viewHolder.plzs = convertView.findViewById(R.id.plzs);
            viewHolder.fbsj = convertView.findViewById(R.id.fbsj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_A_ListView shuJv_a_listView = getItem(position);
        Glide.with(getContext())
                .load(App.url + shuJv_a_listView.getCover())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.bt.setText(shuJv_a_listView.getTitle());
        viewHolder.nr.setText(Html.fromHtml(shuJv_a_listView.getContent()));
        viewHolder.plzs.setText("评论总数：" + shuJv_a_listView.getCommentNum());
        viewHolder.fbsj.setText("发布时间：" + shuJv_a_listView.getPublishDate());
        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView bt;
        TextView nr;
        TextView plzs;
        TextView fbsj;
    }
}
