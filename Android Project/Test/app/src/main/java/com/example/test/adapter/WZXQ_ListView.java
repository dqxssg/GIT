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
import com.example.test.bean.ShuJv_WZXQ_ListView;

import java.util.ArrayList;

public class WZXQ_ListView extends ArrayAdapter<ShuJv_WZXQ_ListView> {
    public WZXQ_ListView(@NonNull Context context,ArrayList<ShuJv_WZXQ_ListView> shuJv_wzxq_listViews) {
        super(context, 0, shuJv_wzxq_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wzxq_listview, parent, false);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShuJv_WZXQ_ListView shuJv_wzxq_listView = getItem(position);
        viewHolder.text.setText(shuJv_wzxq_listView.getContent());
        return convertView;
    }

    class ViewHolder {
        TextView text;
    }
}
