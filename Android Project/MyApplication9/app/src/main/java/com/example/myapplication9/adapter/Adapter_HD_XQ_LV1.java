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
import com.example.myapplication9.bean.S_HD_XQ_LV1;

import java.util.ArrayList;

public class Adapter_HD_XQ_LV1 extends ArrayAdapter<S_HD_XQ_LV1> {
    public Adapter_HD_XQ_LV1(@NonNull Context context, ArrayList<S_HD_XQ_LV1> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodelr viewHodelr = new ViewHodelr();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hd_xq_lv1, parent, false);
            viewHodelr.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHodelr);
        } else {
            viewHodelr = (ViewHodelr) convertView.getTag();
        }
        S_HD_XQ_LV1 s_hd_xq_lv1 = getItem(position);
        viewHodelr.text.setText(s_hd_xq_lv1.getCommitContent());
        return convertView;
    }

    private class ViewHodelr {
        TextView text;
    }
}
