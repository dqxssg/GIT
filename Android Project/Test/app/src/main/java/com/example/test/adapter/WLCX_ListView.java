package com.example.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.R;
import com.example.test.bean.ShuJv_WLCX_ListView;

import java.util.ArrayList;

public class WLCX_ListView extends ArrayAdapter<ShuJv_WLCX_ListView> {
    public WLCX_ListView(@NonNull Context context, ArrayList<ShuJv_WLCX_ListView> shuJv_wlcx_listViews) {
        super(context, 0, shuJv_wlcx_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wlcx_listview, parent, false);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_WLCX_ListView shuJv_wlcx_listView = getItem(position);
        viewHolder.text.setText(shuJv_wlcx_listView.getName());
        return convertView;
    }

    class ViewHolder {
        TextView text;
    }
}
