package com.example.myapplication6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication6.R;

import java.util.ArrayList;

public class Adapter_LJFL_FL_GV extends ArrayAdapter<S_LJFL_FL_GV> {
    public Adapter_LJFL_FL_GV(@NonNull Context context, ArrayList<S_LJFL_FL_GV> s_ljfl_ss_lvs) {
        super(context, 0, s_ljfl_ss_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fljfl_fl_gv, parent, false);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_LJFL_FL_GV s_ljfl_ss_lv = getItem(position);
        viewHolder.text.setText(s_ljfl_ss_lv.getKeyword());
        return convertView;
    }

    private class ViewHolder {
        TextView text;
    }
}
