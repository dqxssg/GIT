package com.example.myapplication2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication2.R;
import com.example.myapplication2.bean.SJ_LXQ_B_LV;
import com.example.myapplication2.util.App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Ad_LXQ_B_LV extends ArrayAdapter<SJ_LXQ_B_LV> {
    public Ad_LXQ_B_LV(@NonNull Context context, ArrayList<SJ_LXQ_B_LV> sj_lxq_b_lvs) {
        super(context, 0, sj_lxq_b_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_lxq_b, parent, false);
            viewHolder.img = convertView.findViewById(R.id.img);
            viewHolder.nc = convertView.findViewById(R.id.nc);
            viewHolder.pjnr = convertView.findViewById(R.id.pjnr);
            viewHolder.pjsj = convertView.findViewById(R.id.pjsj);
            viewHolder.dzs = convertView.findViewById(R.id.dzs);
            viewHolder.line = convertView.findViewById(R.id.line);
            viewHolder.dz = convertView.findViewById(R.id.dz);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SJ_LXQ_B_LV sj_lxq_b_lv = getItem(position);
        Glide.with(getContext())
                .load(App.url + sj_lxq_b_lv.getFromUserAvatar())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.img);
        viewHolder.nc.setText(sj_lxq_b_lv.getFromUserName());
        viewHolder.pjnr.setText(sj_lxq_b_lv.getEvaluateContent());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(sj_lxq_b_lv.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDateStr = sdf.format(date);
        viewHolder.pjsj.setText(newDateStr);
        viewHolder.dzs.setText("" + sj_lxq_b_lv.getLikeCount());
        ViewHolder finalViewHolder = viewHolder;
        finalViewHolder.dz.setColorFilter(Color.parseColor("#000000"));
        viewHolder.line.setOnClickListener(v -> {
            if (sj_lxq_b_lv.getMyLikeState()) {
                finalViewHolder.dzs.setText(sj_lxq_b_lv.getLikeCount() - 1+"");
                finalViewHolder.dz.setColorFilter(Color.parseColor("#000000"));
            } else {
                finalViewHolder.dzs.setText(sj_lxq_b_lv.getLikeCount() + 1+"");
                finalViewHolder.dz.setColorFilter(Color.parseColor("#ff0000"));
            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView img;
        TextView nc;
        TextView pjnr;
        TextView pjsj;
        TextView dzs;
        LinearLayout line;
        ImageView dz;
    }
}
