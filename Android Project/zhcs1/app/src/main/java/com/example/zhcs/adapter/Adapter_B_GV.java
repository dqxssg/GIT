package com.example.zhcs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhcs.R;
import com.example.zhcs.bean.S_A_GV;
import com.example.zhcs.util.App;

import java.util.ArrayList;

public class Adapter_B_GV extends ArrayAdapter<S_A_GV> {
    public Adapter_B_GV(@NonNull Context context, ArrayList<S_A_GV> s_a_gvArrayList) {
        super(context, 0, s_a_gvArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewhOlder viewhOlder = new ViewhOlder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_a_gv, parent, false);
            viewhOlder.image = convertView.findViewById(R.id.image);
            viewhOlder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewhOlder);
        } else {
            viewhOlder = (ViewhOlder) convertView.getTag();
        }
        S_A_GV s_a_gv = getItem(position);
        Glide.with(getContext())
                .load(App.url + s_a_gv.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewhOlder.image);
        viewhOlder.text.setText(s_a_gv.getServiceName());
        return convertView;
    }

    private class ViewhOlder {
        ImageView image;
        TextView text;
    }
}
