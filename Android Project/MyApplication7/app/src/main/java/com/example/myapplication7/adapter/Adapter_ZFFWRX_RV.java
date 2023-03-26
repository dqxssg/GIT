package com.example.myapplication7.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication7.R;
import com.example.myapplication7.bean.S_ZFFWRX_RV;
import com.example.myapplication7.ui.SQLB;
import com.example.myapplication7.ui.XJSQ;
import com.example.myapplication7.util.App;

import java.util.ArrayList;

public class Adapter_ZFFWRX_RV extends RecyclerView.Adapter<Adapter_ZFFWRX_RV.ViewHolder> {
    private Context context;
    private ArrayList<S_ZFFWRX_RV> s_zffwrx_rvs;

    public Adapter_ZFFWRX_RV(Context context, ArrayList<S_ZFFWRX_RV> s_zffwrx_rvs) {
        this.context = context;
        this.s_zffwrx_rvs = s_zffwrx_rvs;
    }

    @NonNull
    @Override
    public Adapter_ZFFWRX_RV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_zffwrx_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ZFFWRX_RV.ViewHolder holder, int position) {
        final S_ZFFWRX_RV s_zffwrx_rv = s_zffwrx_rvs.get(position);
        if (s_zffwrx_rv == null) {
            return;
        }
        Glide.with(context)
                .load(App.url + s_zffwrx_rv.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(holder.image);
        holder.text.setText(s_zffwrx_rv.getName());

        holder.image.setOnClickListener(v -> {
            if (s_zffwrx_rv.getId()==23) {
                context.startActivity(new Intent(context, XJSQ.class));
            } else {
                Intent intent = new Intent(context, SQLB.class);
                intent.putExtra("id", s_zffwrx_rv.getId());
                context.startActivity(intent);
            }
        });

        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        int w = context.getResources().getDisplayMetrics().widthPixels;
        params.width = w / 4;
        holder.itemView.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return s_zffwrx_rvs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
        }
    }
}
