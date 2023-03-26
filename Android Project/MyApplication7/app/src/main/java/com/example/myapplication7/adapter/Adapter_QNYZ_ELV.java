package com.example.myapplication7.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication7.R;
import com.example.myapplication7.bean.S_QNYZ_ELV;
import com.example.myapplication7.ui.YZXQ;
import com.example.myapplication7.util.App;

import java.util.ArrayList;

public class Adapter_QNYZ_ELV extends BaseExpandableListAdapter {
    private ArrayList<S_QNYZ_ELV> s_qnyz_elvs;

    public Adapter_QNYZ_ELV(ArrayList<S_QNYZ_ELV> s_qnyz_elvs) {
        this.s_qnyz_elvs = s_qnyz_elvs;
    }

    @Override
    public int getGroupCount() {
        return s_qnyz_elvs.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = new GroupViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.yi, parent, false);
            groupViewHolder.image = convertView.findViewById(R.id.image);
            groupViewHolder.mc = convertView.findViewById(R.id.mc);
            groupViewHolder.zdjs = convertView.findViewById(R.id.zdjs);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        Glide.with(convertView)
                .load(App.url + s_qnyz_elvs.get(groupPosition).getCoverImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(groupViewHolder.image);
        groupViewHolder.mc.setText(s_qnyz_elvs.get(groupPosition).getName());
        int finalGroupPosition = groupPosition;
        groupViewHolder.zdjs.setOnClickListener(v -> {
            Intent intent = new Intent(parent.getContext(), YZXQ.class);
            intent.putExtra("id", s_qnyz_elvs.get(finalGroupPosition).getId());
            parent.getContext().startActivity(intent);
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = new ChildViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.er, parent, false);
            childViewHolder.nan = convertView.findViewById(R.id.nan);
            childViewHolder.nv = convertView.findViewById(R.id.nv);
            childViewHolder.dz = convertView.findViewById(R.id.dz);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.nan.setText("剩余床铺男：" + s_qnyz_elvs.get(groupPosition).getBedsCountBoy());
        childViewHolder.nv.setText("剩余床铺女：" + s_qnyz_elvs.get(groupPosition).getBedsCountGirl());
        childViewHolder.dz.setText("详细地址：" + s_qnyz_elvs.get(groupPosition).getAddress());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupViewHolder {
        ImageView image;
        TextView mc;
        TextView zdjs;
    }

    private class ChildViewHolder {
        TextView nan;
        TextView nv;
        TextView dz;
    }
}
