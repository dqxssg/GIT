package com.example.zhcs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zhcs.R;
import com.example.zhcs.bean.S_DDXQ_YD;

import java.util.ArrayList;

public class Adapter_DDXQ_YD  extends ArrayAdapter<S_DDXQ_YD> {
        public Adapter_DDXQ_YD(@NonNull Context context, ArrayList<S_DDXQ_YD> s_ddxq_yds) {
            super(context, 0, s_ddxq_yds);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHodler viewHodler = new ViewHodler();
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ddxq_yd, parent, false);
                viewHodler.sj = convertView.findViewById(R.id.sj);
                viewHodler.text1 = convertView.findViewById(R.id.text1);
                viewHodler.text2 = convertView.findViewById(R.id.text2);
                convertView.setTag(viewHodler);
            } else {
                viewHodler = (ViewHodler) convertView.getTag();
            }
            S_DDXQ_YD s_ddxq_yd = getItem(position);
            viewHodler.sj.setText(s_ddxq_yd.getEventAt());
            viewHodler.text1.setText(s_ddxq_yd.getStateName());
            viewHodler.text2.setText(s_ddxq_yd.getAddressAt());
            return convertView;
        }

        class ViewHodler {
            TextView sj;
            TextView text1;
            TextView text2;
        }
    }
