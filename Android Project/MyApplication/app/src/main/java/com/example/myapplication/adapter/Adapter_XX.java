package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.bean.ShuJv_XX;

import java.util.ArrayList;

public class Adapter_XX extends ArrayAdapter<ShuJv_XX> {
    public Adapter_XX(@NonNull Context context, ArrayList<ShuJv_XX> shuJv_xxes) {
        super(context, 0, shuJv_xxes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodler viewHolder = new ViewHodler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_xx, parent, false);
            viewHolder.sjh = convertView.findViewById(R.id.sjh);
            viewHolder.yys = convertView.findViewById(R.id.yys);
            viewHolder.sj = convertView.findViewById(R.id.sj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHodler) convertView.getTag();
        }
        ShuJv_XX shuJv_xx = getItem(position);
        viewHolder.sjh.setText(shuJv_xx.getSjh());
        viewHolder.yys.setText(shuJv_xx.getYys());
        viewHolder.sj.setText(shuJv_xx.getSj());
        return convertView;
    }

    class ViewHodler {
        TextView sjh;
        TextView yys;
        TextView sj;
    }
}
