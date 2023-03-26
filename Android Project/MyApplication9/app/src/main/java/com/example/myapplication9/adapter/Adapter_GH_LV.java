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
import com.example.myapplication9.bean.S_GH_LV;

import java.util.ArrayList;

public class Adapter_GH_LV extends ArrayAdapter<S_GH_LV> {
    public Adapter_GH_LV(@NonNull Context context, ArrayList<S_GH_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewhOdler viewhOdler = new ViewhOdler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gh_lv, parent, false);
            viewhOdler.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewhOdler);
        } else {
            viewhOdler = (ViewhOdler) convertView.getTag();
        }
        S_GH_LV s_gh_lv = getItem(position);
        viewhOdler.text.setText(s_gh_lv.getTime());
        return convertView;
    }

    private class ViewhOdler {
        TextView text;
    }
}
