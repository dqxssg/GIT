package com.example.myapplication10.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication10.R;
import com.example.myapplication10.bean.S_DDLB_XQ_LV;

import java.util.ArrayList;

public class Adapter_DDLB_XQ_LV extends ArrayAdapter<S_DDLB_XQ_LV> {
    public Adapter_DDLB_XQ_LV(@NonNull Context context, ArrayList<S_DDLB_XQ_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodler viewHodler = new ViewHodler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ddlb_xq_lv, parent, false);
            viewHodler.dz = convertView.findViewById(R.id.dz);
            viewHodler.wp = convertView.findViewById(R.id.wp);
            viewHodler.je = convertView.findViewById(R.id.je);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        S_DDLB_XQ_LV s_ddlb_xq_lv = getItem(position);
        viewHodler.dz.setText(s_ddlb_xq_lv.getBusiness());
        viewHodler.wp.setText("购买"+s_ddlb_xq_lv.getCommodity());
        viewHodler.je.setText("花费"+s_ddlb_xq_lv.getPrice()+"元");
        return convertView;
    }

    private class ViewHodler {
        TextView dz;
        TextView wp;
        TextView je;
    }
}


