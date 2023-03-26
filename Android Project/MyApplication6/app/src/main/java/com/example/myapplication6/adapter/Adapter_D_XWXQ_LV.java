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
import com.example.myapplication6.bean.S_D_XWXQ_LV;

import java.util.ArrayList;

public class Adapter_D_XWXQ_LV extends ArrayAdapter<S_D_XWXQ_LV> {

    public Adapter_D_XWXQ_LV(@NonNull Context context, ArrayList<S_D_XWXQ_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_d_xwxq_lv, parent, false);
            viewHolder.text = (TextView) convertView .findViewById(R.id.text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        S_D_XWXQ_LV s_d_xwxq_lv=getItem(position);
        viewHolder.text.setText(s_d_xwxq_lv.getContent()+"");
        return convertView;
    }

    private class ViewHolder {
        private TextView text;
    }
}
