package com.example.myapplication7.adapter;

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
import com.example.myapplication7.R;
import com.example.myapplication7.bean.S_AA_LV;
import com.example.myapplication7.util.App;

import java.util.ArrayList;

public class Adapter_AA_LV extends ArrayAdapter<S_AA_LV> {

    public Adapter_AA_LV(@NonNull Context context, ArrayList<S_AA_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_aa_lv, parent, false);
            viewHolder.image =  convertView.findViewById(R.id.image);
            viewHolder.zt = convertView.findViewById(R.id.zt);
            viewHolder.nr =  convertView.findViewById(R.id.nr);
            viewHolder.pl =  convertView.findViewById(R.id.pl);
            viewHolder.sj =  convertView.findViewById(R.id.sj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_AA_LV s_aa_lv = getItem(position);
        Glide.with(getContext())
                .load(App.url + s_aa_lv.getCover())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.zt.setText(s_aa_lv.getTitle());
        viewHolder.nr.setText(Html.fromHtml(s_aa_lv.getContent()));
        viewHolder.pl.setText("评论：" + s_aa_lv.getCommentNum());
        viewHolder.sj.setText(s_aa_lv.getCreateTime());
        return convertView;
    }


    private class ViewHolder {
        private ImageView image;
        private TextView zt;
        private TextView nr;
        private TextView pl;
        private TextView sj;
    }
}
