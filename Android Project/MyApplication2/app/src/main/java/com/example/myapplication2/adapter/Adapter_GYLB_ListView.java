package com.example.myapplication2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication2.R;
import com.example.myapplication2.bean.ShuJv_GYLB_ListView;

import java.util.ArrayList;

public class Adapter_GYLB_ListView extends ArrayAdapter<ShuJv_GYLB_ListView> {
    public Adapter_GYLB_ListView(@NonNull Context context, ArrayList<ShuJv_GYLB_ListView> shuJv_gylb_listViews) {
        super(context, 0, shuJv_gylb_listViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHodler viewHodler = new ViewHodler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gylb_listview, parent, false);
            viewHodler.gylb = convertView.findViewById(R.id.gylb);
            viewHodler.fbr = convertView.findViewById(R.id.fbr);
            viewHodler.ycsk = convertView.findViewById(R.id.ycsk);
            viewHodler.xmsj = convertView.findViewById(R.id.xmsj);
            viewHodler.cjrs = convertView.findViewById(R.id.cjrs);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
        ShuJv_GYLB_ListView shuJv_gylb_listView = getItem(position);
        viewHodler.gylb.setText("公益类别：" + shuJv_gylb_listView.getType().getName());
        viewHodler.fbr.setText("发布人：" + shuJv_gylb_listView.getAuthor());
        viewHodler.ycsk.setText("已筹善款：" + shuJv_gylb_listView.getMoneyNow());
        viewHodler.xmsj.setText("项目时间：" + shuJv_gylb_listView.getActivityAt());
        viewHodler.cjrs.setText("参捐人数：" + shuJv_gylb_listView.getDonateCount());
        return convertView;
    }

    class ViewHodler {
        TextView gylb;
        TextView fbr;
        TextView ycsk;
        TextView xmsj;
        TextView cjrs;
    }
}
