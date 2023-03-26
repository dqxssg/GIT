package com.example.myapplication4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication4.R;
import com.example.myapplication4.bean.S_WDYY_LV;

import java.util.ArrayList;

public class Adapter_WDYY_LV extends ArrayAdapter<S_WDYY_LV> {
    public Adapter_WDYY_LV(@NonNull Context context, ArrayList<S_WDYY_LV> s_wdyy_lvs) {
        super(context, 0, s_wdyy_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodler viewHodler = new ViewHodler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_wdyy_lv, parent, false);
            viewHodler.cph = convertView.findViewById(R.id.cph);
            viewHodler.yydd = convertView.findViewById(R.id.yydd);
            viewHodler.yydj = convertView.findViewById(R.id.yydj);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        S_WDYY_LV s_wdyy_lv = getItem(position);
        viewHodler.cph.setText("车牌号：" + s_wdyy_lv.getPlateNo());
        viewHodler.yydd.setText("预约地点：" + s_wdyy_lv.getPlaceName());
        viewHodler.yydj.setText("预约时间：" + s_wdyy_lv.getAptTime());
        return convertView;
    }

    private class ViewHodler {
        TextView cph;
        TextView yydd;
        TextView yydj;
    }
}
