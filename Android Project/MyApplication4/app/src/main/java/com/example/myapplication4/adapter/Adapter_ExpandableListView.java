package com.example.myapplication4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.myapplication4.R;
import com.example.myapplication4.bean.S_ZHBS_ER;
import com.example.myapplication4.bean.S_ZHBS_YI;

import java.util.ArrayList;

public class Adapter_ExpandableListView extends BaseExpandableListAdapter {
    private ArrayList<S_ZHBS_YI> s_zhbs_yis = new ArrayList<>();
    private ArrayList<ArrayList<S_ZHBS_ER>> arrayLists = new ArrayList<>();

    public Adapter_ExpandableListView(ArrayList<S_ZHBS_YI> zhbs_zds, ArrayList<ArrayList<S_ZHBS_ER>> zd) {
        this.s_zhbs_yis = zhbs_zds;
        this.arrayLists = zd;
    }

    @Override
    public int getGroupCount() {
        return s_zhbs_yis.size();
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
        GroupView groupView = new GroupView();
        if (convertView == null) {
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.yi,parent,false);
            groupView.qd = convertView.findViewById(R.id.qd);
            groupView.qdsj = convertView.findViewById(R.id.qdsj);
            groupView.lx = convertView.findViewById(R.id.lx);
            groupView.zd = convertView.findViewById(R.id.zd);
            groupView.zdsj = convertView.findViewById(R.id.zdsj);
            groupView.pj = convertView.findViewById(R.id.pj);
            groupView.lc = convertView.findViewById(R.id.lc);
            convertView.setTag(groupView);
        } else {
            groupView = (GroupView) convertView.getTag();
        }
        groupView.qd.setText(s_zhbs_yis.get(groupPosition).getFirst());
        groupView.qdsj.setText(s_zhbs_yis.get(groupPosition).getStartTime());
        groupView.lx.setText(s_zhbs_yis.get(groupPosition).getName());
        groupView.zd.setText(s_zhbs_yis.get(groupPosition).getEnd());
        groupView.zdsj.setText(s_zhbs_yis.get(groupPosition).getEndTime());
        groupView.pj.setText("票价："+s_zhbs_yis.get(groupPosition).getPrice() + "元");
        groupView.lc.setText("里程："+s_zhbs_yis.get(groupPosition).getMileage() + "km");
        groupView.lx.setOnClickListener(v -> {
            onItemListener.onClick(groupPosition);
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChiliView chiliView = new ChiliView();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.er, parent, false);
            chiliView.zd = convertView.findViewById(R.id.zd);
            convertView.setTag(chiliView);
        } else {
            chiliView = (ChiliView) convertView.getTag();
        }
        chiliView.zd.setText(arrayLists.get(groupPosition).get(childPosition).getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class GroupView {
        TextView qd;
        TextView qdsj;
        TextView lx;
        TextView zd;
        TextView zdsj;
        TextView pj;
        TextView lc;
    }

    private class ChiliView {
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
