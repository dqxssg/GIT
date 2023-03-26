package com.example.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.bean.ShuJv_ZHBS;
import com.example.test.bean.ShuJv_ZHBS_ZD;

import java.util.ArrayList;

public class ZHBD_Adapter extends BaseExpandableListAdapter {
    private ArrayList<ShuJv_ZHBS> shuJv_zhbs;
    private ArrayList<ArrayList<ShuJv_ZHBS_ZD>> arrayLists;

    public ZHBD_Adapter(ArrayList<ShuJv_ZHBS> shuJv_zhbs, ArrayList<ArrayList<ShuJv_ZHBS_ZD>> arrayLists) {
        this.arrayLists = arrayLists;
        this.shuJv_zhbs = shuJv_zhbs;
    }

    @Override
    public int getGroupCount() {
        return shuJv_zhbs.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_zhbs, parent, false);
            groupViewHolder.lxmc = convertView.findViewById(R.id.lxmc);
            groupViewHolder.qd = convertView.findViewById(R.id.qd);
            groupViewHolder.zd = convertView.findViewById(R.id.zd);
            groupViewHolder.yxsm = convertView.findViewById(R.id.yxsm);
            groupViewHolder.pj = convertView.findViewById(R.id.pj);
            groupViewHolder.lc = convertView.findViewById(R.id.lc);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.lxmc.setText(shuJv_zhbs.get(groupPosition).getName());
        groupViewHolder.qd.setText("起始点：" + shuJv_zhbs.get(groupPosition).getFirst());
        groupViewHolder.zd.setText(shuJv_zhbs.get(groupPosition).getEnd());
        groupViewHolder.yxsm.setText("运行时间：" + shuJv_zhbs.get(groupPosition).getStartTime() + "-" + shuJv_zhbs.get(groupPosition).getEndTime());
        groupViewHolder.pj.setText("票价：" + shuJv_zhbs.get(groupPosition).getPrice() + "元");
        groupViewHolder.lc.setText("运行里程：" + shuJv_zhbs.get(groupPosition).getMileage() + "km");
        groupViewHolder.lxmc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onClick(groupPosition);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder=new ChildViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_zhbs, parent, false);
            childViewHolder.zd = convertView.findViewById(R.id.zd);
            convertView.setTag(childPosition);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        for (int i = 0; i < arrayLists.get(groupPosition).size(); i++) {
            childViewHolder.zd.setText(arrayLists.get(groupPosition).get(childPosition).getName());
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        TextView lxmc;
        TextView qd;
        TextView zd;
        TextView yxsm;
        TextView pj;
        TextView lc;
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
