package com.example.myapplication6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication6.R;
import com.example.myapplication6.bean.S_SZTSGPL_LV;

import java.util.ArrayList;

public class Adapter_SZTSGPL_LV extends ArrayAdapter<S_SZTSGPL_LV> {
    public Adapter_SZTSGPL_LV(@NonNull Context context, ArrayList<S_SZTSGPL_LV> s_sztsgpl_lvs) {
        super(context, 0, s_sztsgpl_lvs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sztsgpl_lv, parent, false);
            viewHolder.plr = convertView.findViewById(R.id.plr);
            viewHolder.nr = convertView.findViewById(R.id.nr);
            viewHolder.dz = convertView.findViewById(R.id.dz);
            viewHolder.dzan = convertView.findViewById(R.id.dzan);
            viewHolder.dzs = convertView.findViewById(R.id.dzs);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_SZTSGPL_LV s_sztsgpl_lv = getItem(position);
        return convertView;
    }

    private class ViewHolder {
        TextView plr;
        TextView nr;
        LinearLayout dz;
        ImageView dzan;
        TextView dzs;
    }
}
