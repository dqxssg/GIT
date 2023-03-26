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
import com.example.myapplication9.bean.S_MZKSFL_LV;

import java.util.ArrayList;

public class Adapter_MZKSFL_LV extends ArrayAdapter<S_MZKSFL_LV> {
    public Adapter_MZKSFL_LV(@NonNull Context context, ArrayList<S_MZKSFL_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mzksfl_lv, parent, false);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_MZKSFL_LV s_mzksfl_lv = getItem(position);
        viewHolder.text.setText(s_mzksfl_lv.getDeptName());
        return convertView;
    }

    private class ViewHolder {
        TextView text;
    }
}
