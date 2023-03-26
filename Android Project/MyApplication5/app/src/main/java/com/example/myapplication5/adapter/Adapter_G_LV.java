package com.example.myapplication5.adapter;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication5.R;
import com.example.myapplication5.bean.S_D_RV;
import com.example.myapplication5.bean.S_G_LV;
import com.example.myapplication5.util.App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Adapter_G_LV extends ArrayAdapter<S_G_LV> {
    private int flag = 0;

    public Adapter_G_LV(@NonNull Context context, ArrayList<S_G_LV> shuJv_lsxq_listViews) {
        super(context, 0, shuJv_lsxq_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodler viewHodler = new ViewHodler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_g_lv, parent, false);
            viewHodler.img = convertView.findViewById(R.id.img);
            viewHodler.nc = convertView.findViewById(R.id.nc);
            viewHodler.pjnr = convertView.findViewById(R.id.pjnr);
            viewHodler.pjsj = convertView.findViewById(R.id.pjsj);
            viewHodler.dzs = convertView.findViewById(R.id.dzs);
            viewHodler.line = convertView.findViewById(R.id.line);
            viewHodler.dz = convertView.findViewById(R.id.dz);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        S_G_LV sj_lxq_b_lv = getItem(position);
        Glide.with(getContext())
                .load(App.url + sj_lxq_b_lv.getFromUserAvatar())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHodler.img);
        viewHodler.nc.setText(sj_lxq_b_lv.getFromUserName());
        viewHodler.pjnr.setText(sj_lxq_b_lv.getEvaluateContent());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(sj_lxq_b_lv.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDateStr = sdf.format(date);
        viewHodler.pjsj.setText(newDateStr);
        viewHodler.dzs.setText("" + sj_lxq_b_lv.getLikeCount());
        viewHodler.dz.setColorFilter(Color.parseColor("#000000"));
        ViewHodler finalViewHodler = viewHodler;
        viewHodler.line.setOnClickListener(v -> {
            if (flag == 0) {
                finalViewHodler.dzs.setText(sj_lxq_b_lv.getLikeCount() + 1 + "");
                finalViewHodler.dz.setColorFilter(Color.parseColor("#ff0000"));
                flag++;
            } else if (flag == 1) {
                finalViewHodler.dzs.setText(sj_lxq_b_lv.getLikeCount() + "");
                finalViewHodler.dz.setColorFilter(Color.parseColor("#000000"));
                flag--;
            }
        });
        return convertView;
    }

    class ViewHodler {
        ImageView img;
        TextView nc;
        TextView pjnr;
        TextView pjsj;
        TextView dzs;
        LinearLayout line;
        ImageView dz;
    }
}
