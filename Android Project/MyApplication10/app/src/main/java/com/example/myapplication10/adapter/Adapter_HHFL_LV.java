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
import com.example.myapplication10.bean.S_HHFL_LV;

import java.util.ArrayList;

public class Adapter_HHFL_LV extends ArrayAdapter<S_HHFL_LV> {
    public Adapter_HHFL_LV(@NonNull Context context, ArrayList<S_HHFL_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewhOlder viewhOlder = new ViewhOlder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hhgl_lv, parent, false);
            viewhOlder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewhOlder);
        } else {
            viewhOlder = (ViewhOlder) convertView.getTag();
        }
        S_HHFL_LV s_hhfl_lv = getItem(position);
        viewhOlder.text.setText(s_hhfl_lv.getGroupName());
        return convertView;
    }

    private class ViewhOlder {
        TextView text;
    }
}
