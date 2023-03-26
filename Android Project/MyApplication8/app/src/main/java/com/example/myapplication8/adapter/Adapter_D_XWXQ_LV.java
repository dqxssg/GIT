package com.example.myapplication8.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication8.R;
import com.example.myapplication8.bean.S_D_XWXQ_LV;

import java.util.ArrayList;

public class Adapter_D_XWXQ_LV extends ArrayAdapter<S_D_XWXQ_LV> {
    public Adapter_D_XWXQ_LV(@NonNull Context context, ArrayList<S_D_XWXQ_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHOdler viewHOdler = new ViewHOdler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_d_xwxq_lv, parent, false);
            viewHOdler.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHOdler);
        } else {
            viewHOdler = (ViewHOdler) convertView.getTag();
        }
        S_D_XWXQ_LV s_d_xwxq_lv=getItem(position);
        viewHOdler.text.setText(s_d_xwxq_lv.getCommit());
        return convertView;
    }

    private class ViewHOdler {
        TextView text;
    }
}
