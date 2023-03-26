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
import com.example.myapplication5.bean.S_ZXLB_LV;
import com.example.myapplication5.bean.S_ZXLB_TX;
import com.example.myapplication5.util.App;

import java.util.ArrayList;

public class Adapter_ZXLB_LV extends ArrayAdapter<S_ZXLB_LV> {
    private ArrayList<S_ZXLB_TX> s_zxlb_txes;
    public Adapter_ZXLB_LV(@NonNull Context context, ArrayList<S_ZXLB_LV> s_zxlb_lvs,ArrayList<S_ZXLB_TX> s_zxlb_txes) {
        super(context, 0, s_zxlb_lvs);
        this.s_zxlb_txes=s_zxlb_txes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zxlb_lv, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.mc = convertView.findViewById(R.id.mc);
            viewHolder.flzc = convertView.findViewById(R.id.flzc);
            viewHolder.slzt = convertView.findViewById(R.id.slzt);
            viewHolder.tjsj = convertView.findViewById(R.id.tjsj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_ZXLB_LV s_zxlb_lv = getItem(position);
        Glide.with(getContext())
                .load(App.url + s_zxlb_txes.get(position).getTx())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.mc.setText(s_zxlb_lv.getLawyerName());
        viewHolder.flzc.setText("法律专长："+(CharSequence) s_zxlb_lv.getLegalExpertiseName());
        if (s_zxlb_lv.getState().equals("0")) {
            viewHolder.slzt.setText("未受理");
        } else {
            viewHolder.slzt.setText("已受理");
        }
        viewHolder.tjsj.setText(s_zxlb_lv.getCreateTime());
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView mc;
        TextView flzc;
        TextView slzt;
        TextView tjsj;
    }
}
