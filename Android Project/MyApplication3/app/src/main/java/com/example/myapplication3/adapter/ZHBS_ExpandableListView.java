package com.example.myapplication3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.myapplication3.R;
import com.example.myapplication3.bean.ShuJv_ZHBS_ExpandableListView;
import com.example.myapplication3.bean.ShuJv_ZHBS_ZD;

import java.util.ArrayList;

public class ZHBS_ExpandableListView extends BaseExpandableListAdapter {
    private ArrayList<ShuJv_ZHBS_ExpandableListView> shuJv_zhbs_expandableListViews;
    private ArrayList<ArrayList<ShuJv_ZHBS_ZD>> arrayLists;

    public ZHBS_ExpandableListView(ArrayList<ShuJv_ZHBS_ExpandableListView> shuJv_zhbs_expandableListViews, ArrayList<ArrayList<ShuJv_ZHBS_ZD>> arrayLists) {
        this.arrayLists = arrayLists;
        this.shuJv_zhbs_expandableListViews = shuJv_zhbs_expandableListViews;
    }

    @Override
    public int getGroupCount() {
        return shuJv_zhbs_expandableListViews.size();
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
        GroupViewHolder groupViewHolder = new GroupViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.yi, parent, false);
            groupViewHolder.lx = convertView.findViewById(R.id.lx);
            groupViewHolder.qd = convertView.findViewById(R.id.qd);
            groupViewHolder.zd = convertView.findViewById(R.id.zd);
            groupViewHolder.kssj = convertView.findViewById(R.id.kssj);
            groupViewHolder.lc = convertView.findViewById(R.id.lc);
            groupViewHolder.pj = convertView.findViewById(R.id.pj);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.lx.setText(shuJv_zhbs_expandableListViews.get(groupPosition).getName());
        groupViewHolder.qd.setText("起点：" + shuJv_zhbs_expandableListViews.get(groupPosition).getFirst());
        groupViewHolder.zd.setText("终点：" + shuJv_zhbs_expandableListViews.get(groupPosition).getEnd());
        groupViewHolder.kssj.setText("运行时间：" + shuJv_zhbs_expandableListViews.get(groupPosition).getStartTime() + "--" + shuJv_zhbs_expandableListViews.get(groupPosition).getEndTime());
        groupViewHolder.lc.setText("里程：" + shuJv_zhbs_expandableListViews.get(groupPosition).getMileage() + "km");
        groupViewHolder.pj.setText("票价：" + shuJv_zhbs_expandableListViews.get(groupPosition).getPrice() + "元");
        groupViewHolder.lx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onClick(groupPosition);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = new ChildViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.er, parent, false);
            childViewHolder.zd = convertView.findViewById(R.id.zd);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.zd.setText(arrayLists.get(groupPosition).get(childPosition).getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        TextView lx;
        TextView qd;
        TextView zd;
        TextView kssj;
        TextView lc;
        TextView pj;
    }

    class ChildViewHolder {
        TextView zd;
    }

    public interface onItemListener {
        void onClick(int i);
    }

    public onItemListener onItemListener;

    public void setOnItemListener(onItemListener onItemListener) {
        this.onItemListener = onItemListener;

    }
}
