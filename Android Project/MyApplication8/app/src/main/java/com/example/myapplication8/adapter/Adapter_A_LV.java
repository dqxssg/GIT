package com.example.myapplication8.adapter;

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
import com.example.myapplication8.R;
import com.example.myapplication8.bean.S_A_LV;
import com.example.myapplication8.bean.S_A_LV_QT;
import com.example.myapplication8.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Adapter_A_LV extends ArrayAdapter<S_A_LV> {
    private ArrayList<S_A_LV_QT> s_a_lv_qtArrayList;

    public Adapter_A_LV(@NonNull Context context, ArrayList<S_A_LV> resource, ArrayList<S_A_LV_QT> s_a_lv_qtArrayList) {
        super(context, 0, resource);
        this.s_a_lv_qtArrayList = s_a_lv_qtArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_a_lv, parent, false);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.bt = convertView.findViewById(R.id.bt);
            viewHolder.nr = convertView.findViewById(R.id.nr);
            viewHolder.plzs = convertView.findViewById(R.id.plzs);
            viewHolder.sj = convertView.findViewById(R.id.sj);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        S_A_LV s_a_lv = getItem(position);
        Glide.with(getContext())
                .load(s_a_lv.getPicture())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(viewHolder.image);
        viewHolder.bt.setText(s_a_lv.getTitle());
        viewHolder.nr.setText(Html.fromHtml(s_a_lv.getAbstractX()));
        viewHolder.plzs.setText("评论总数：" + s_a_lv_qtArrayList.get(position).getRecommand());
        viewHolder.sj.setText(s_a_lv_qtArrayList.get(position).getPublicTime()+"");
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView bt;
        TextView nr;
        TextView plzs;
        TextView sj;
    }
}
