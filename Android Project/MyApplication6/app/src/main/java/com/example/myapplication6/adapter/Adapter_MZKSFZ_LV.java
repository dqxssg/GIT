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
import com.example.myapplication6.bean.S_MZKSFZ_LV;

import java.util.ArrayList;

public class Adapter_MZKSFZ_LV extends ArrayAdapter<S_MZKSFZ_LV> {

    public Adapter_MZKSFZ_LV(@NonNull Context context, ArrayList<S_MZKSFZ_LV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHOdler viewHOdler = new ViewHOdler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mzksfz_lv, parent, false);
            viewHOdler.mz = (TextView) convertView.findViewById(R.id.mz);
            convertView.setTag(viewHOdler);
        } else {
            viewHOdler = (ViewHOdler) convertView.getTag();
        }
        S_MZKSFZ_LV s_mzksfz_lv = getItem(position);
        viewHOdler.mz.setText(s_mzksfz_lv.getCategoryName());
        return convertView;
    }

    private class ViewHOdler {
        private TextView mz;
    }
}
