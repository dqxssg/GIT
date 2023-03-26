package com.example.myapplication10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.myapplication10.R;
import com.example.myapplication10.bean.S_ZHBS_E;
import com.example.myapplication10.bean.S_ZHBS_Y;

import java.util.ArrayList;

public class Adapter_ZHBS_ELV extends BaseExpandableListAdapter {
    private ArrayList<S_ZHBS_Y> s_zhbs_is ;
    private ArrayList<ArrayList<S_ZHBS_E>> arrayLists;

    public Adapter_ZHBS_ELV(ArrayList<S_ZHBS_Y> s_zhbs_is, ArrayList<ArrayList<S_ZHBS_E>> arrayLists) {
        this.s_zhbs_is = s_zhbs_is;
        this.arrayLists = arrayLists;
    }

    @Override
    public int getGroupCount() {
        return s_zhbs_is.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrayLists.get(groupPosition).size();
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
        GroupViewHolder viewHolder = new GroupViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.y, parent, false);
            viewHolder.qd = convertView.findViewById(R.id.qd);
            viewHolder.qdsj = convertView.findViewById(R.id.qdsj);
            viewHolder.lx = convertView.findViewById(R.id.lx);
            viewHolder.zd = convertView.findViewById(R.id.zd);
            viewHolder.zdsj = convertView.findViewById(R.id.zdsj);
            viewHolder.pj = convertView.findViewById(R.id.pj);
            viewHolder.lc = convertView.findViewById(R.id.lc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        viewHolder.qd.setText(s_zhbs_is.get(groupPosition).getStartSite());
        viewHolder.qdsj.setText("夏秋季："+s_zhbs_is.get(groupPosition).getRunTime1());
        viewHolder.lx.setText(s_zhbs_is.get(groupPosition).getPathName());
        viewHolder.zd.setText(s_zhbs_is.get(groupPosition).getEndSite());
        viewHolder.zdsj.setText("春冬季："+s_zhbs_is.get(groupPosition).getRunTime2());
        viewHolder.pj.setText("票价：" + s_zhbs_is.get(groupPosition).getPrice() + "元");
        viewHolder.lc.setText("里程：" + s_zhbs_is.get(groupPosition).getMileage() + "km");
        viewHolder.lx.setOnClickListener(v -> {
            onItemListener.onClick(groupPosition);
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder = new ChildViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ee, parent, false);
            viewHolder.zd = convertView.findViewById(R.id.zd);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        viewHolder.zd.setText(arrayLists.get(groupPosition).get(childPosition).getSiteName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        TextView qd;
        TextView qdsj;
        TextView lx;
        TextView zd;
        TextView zdsj;
        TextView pj;
        TextView lc;
    }

    class ChildViewHolder {
        TextView zd;
    }

    public interface onItemListener {
        void onClick(int position);
    }

    public onItemListener onItemListener;

    public void setOnItemListener(onItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
}
