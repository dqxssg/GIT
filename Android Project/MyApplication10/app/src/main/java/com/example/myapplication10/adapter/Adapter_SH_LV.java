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
import com.example.myapplication10.bean.S_SH_LV;

import java.util.ArrayList;

public class Adapter_SH_LV extends ArrayAdapter<S_SH_LV> {
    public Adapter_SH_LV(@NonNull Context context, ArrayList<S_SH_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHOdler viewHOdler = new ViewHOdler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sh_lv, parent, false);
            viewHOdler.dw = convertView.findViewById(R.id.dw);
            viewHOdler.jfh = convertView.findViewById(R.id.jfh);
            convertView.setTag(viewHOdler);
        } else {
            viewHOdler = (ViewHOdler) convertView.getTag();
        }
        S_SH_LV s_sh_lv = getItem(position);
        viewHOdler.dw.setText("单位："+s_sh_lv.getUnit());
        viewHOdler.jfh.setText("缴费户号："+s_sh_lv.getAccountId());
        return convertView;
    }

    private class ViewHOdler {
        TextView dw;
        TextView jfh;
    }
}
