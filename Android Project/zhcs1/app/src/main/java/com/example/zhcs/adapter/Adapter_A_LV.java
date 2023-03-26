package com.example.zhcs.adapter;

import android.content.Context;
import android.text.Html;
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
import com.example.zhcs.bean.S_A_LV;
import com.example.zhcs.util.App;

import java.util.ArrayList;

public class Adapter_A_LV extends ArrayAdapter<S_A_LV> {
    public Adapter_A_LV(@NonNull Context context, ArrayList<S_A_LV> s_a_lvArrayList) {
        super(context, 0, s_a_lvArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHOlderr viewHOlderr = new ViewHOlderr();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_a_lv, parent, false);
            viewHOlderr.ima = convertView.findViewById(R.id.ima);
            viewHOlderr.bt = convertView.findViewById(R.id.bt);
            viewHOlderr.nr = convertView.findViewById(R.id.nr);
            viewHOlderr.plzs = convertView.findViewById(R.id.plzs);
            viewHOlderr.fbsj = convertView.findViewById(R.id.fbsj);
            convertView.setTag(viewHOlderr);
        } else {
            viewHOlderr = (ViewHOlderr) convertView.getTag();
        }
        S_A_LV s_a_lv = getItem(position);
        Glide.with(getContext())
                .load(App.url + s_a_lv.getCover())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHOlderr.ima);
        viewHOlderr.bt.setText(s_a_lv.getTitle());
        viewHOlderr.nr.setText(Html.fromHtml(s_a_lv.getContent()));
        viewHOlderr.plzs.setText("评论：" + s_a_lv.getCommentNum());
        viewHOlderr.fbsj.setText(  s_a_lv.getUpdateTime());
        return convertView;
    }

    private class ViewHOlderr {
        ImageView ima;
        TextView bt;
        TextView nr;
        TextView plzs;
        TextView fbsj;
    }
}
