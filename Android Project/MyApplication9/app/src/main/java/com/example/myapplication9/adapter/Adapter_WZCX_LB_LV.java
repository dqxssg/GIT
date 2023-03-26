package com.example.myapplication9.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication9.R;
import com.example.myapplication9.bean.S_WZCX_LB_LV;

import java.util.ArrayList;
import java.util.Objects;

public class Adapter_WZCX_LB_LV extends ArrayAdapter<S_WZCX_LB_LV> {
    private int count;

    public Adapter_WZCX_LB_LV(@NonNull Context context, ArrayList<S_WZCX_LB_LV> resource, int count) {
        super(context, 0, resource);
        this.count = count;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHOlder viewHOlder = new ViewHOlder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_wzcx_lb_lb, parent, false);
            viewHOlder.cph = convertView.findViewById(R.id.cph);
            viewHOlder.clzt = convertView.findViewById(R.id.clzt);
            viewHOlder.wzsj = convertView.findViewById(R.id.wzsj);
            viewHOlder.wzje = convertView.findViewById(R.id.wzje);
            viewHOlder.wzjf = convertView.findViewById(R.id.wzjf);
            viewHOlder.wzdd = convertView.findViewById(R.id.wzdd);
            convertView.setTag(viewHOlder);
        } else {
            viewHOlder = (ViewHOlder) convertView.getTag();
        }
        S_WZCX_LB_LV s_wzcx_lb_lv = getItem(position);
        if (position < count) {
            viewHOlder.cph.setText("车牌号："+s_wzcx_lb_lv.getCarid());
            if (Objects.equals(s_wzcx_lb_lv.getStatus(), "0")) {
                viewHOlder.clzt.setText("处理状态：未处理");
            } else {
                viewHOlder.clzt.setText("处理状态：已处理");
            }
            viewHOlder.wzsj.setText("违章时间："+s_wzcx_lb_lv.getTime());
            viewHOlder.wzje.setText("处罚金额："+s_wzcx_lb_lv.getCost());
            viewHOlder.wzjf.setText("违章记分："+s_wzcx_lb_lv.getDeductPoints());
            viewHOlder.wzdd.setText("违章地点："+s_wzcx_lb_lv.getPlace());
        }
        return convertView;
    }

    private class ViewHOlder {
        TextView cph;
        TextView clzt;
        TextView wzsj;
        TextView wzje;
        TextView wzjf;
        TextView wzdd;
    }
}
