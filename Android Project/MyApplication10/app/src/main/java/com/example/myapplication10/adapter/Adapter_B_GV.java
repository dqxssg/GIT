package com.example.myapplication10.adapter;
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
import com.example.myapplication10.R;
import com.example.myapplication10.bean.S_A_GV;

import java.util.ArrayList;

public class Adapter_B_GV  extends ArrayAdapter<S_A_GV> {
    public Adapter_B_GV(@NonNull Context context, ArrayList<S_A_GV> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_a_gv, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_A_GV s_a_gv = getItem(position);
        Glide.with(getContext())
                .load(s_a_gv.getIcon())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.text.setText(s_a_gv.getServiceName());
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView text;
    }
}

