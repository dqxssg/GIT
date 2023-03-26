package com.example.myapplication4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication4.R;
import com.example.myapplication4.bean.S_ZYHD_LV;
import com.example.myapplication4.ui.WDHD;
import com.example.myapplication4.util.Httputil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Adapter_ZYHD_LV extends ArrayAdapter<S_ZYHD_LV> {
    private Context context;

    public Adapter_ZYHD_LV(@NonNull Context context, ArrayList<S_ZYHD_LV> s_zyhd_lvs) {
        super(context, 0, s_zyhd_lvs);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHOdler viewHOdler = new ViewHOdler();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zyhd_lv, parent, false);
            viewHOdler.hdbt = (TextView) convertView.findViewById(R.id.hdbt);
            viewHOdler.cbdw = (TextView) convertView.findViewById(R.id.cbdw);
            viewHOdler.ryyq = (TextView) convertView.findViewById(R.id.ryyq);
            viewHOdler.hdkssj = (TextView) convertView.findViewById(R.id.hdkssj);
            viewHOdler.bm = (TextView) convertView.findViewById(R.id.bm);
            convertView.setTag(viewHOdler);
        } else {
            viewHOdler = (ViewHOdler) convertView.getTag();
        }
        S_ZYHD_LV s_zyhd_lv = getItem(position);
        viewHOdler.hdbt.setText(s_zyhd_lv.getTitle());
        viewHOdler.cbdw.setText(s_zyhd_lv.getUndertaker());
        viewHOdler.ryyq.setText(s_zyhd_lv.getRequireText());
        viewHOdler.hdkssj.setText(s_zyhd_lv.getStartAt());
        viewHOdler.bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onClick(position);
            }
        });
        return convertView;
    }

    private class ViewHOdler {
        private TextView hdbt;
        private TextView cbdw;
        private TextView ryyq;
        private TextView hdkssj;
        private TextView bm;
    }

    public interface onItemListener {
        void onClick(int i);
    }

    public onItemListener onItemListener;

    public void setOnItemListener(onItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
}
