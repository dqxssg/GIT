package com.example.myapplication5.adapter;

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
import com.example.myapplication5.R;
import com.example.myapplication5.bean.S_D_RV;
import com.example.myapplication5.ui.LSLB;
import com.example.myapplication5.util.App;

import java.util.ArrayList;

public class Adapter_D_RV extends RecyclerView.Adapter<Adapter_D_RV.Adapter_ViewHolder> {
    private ArrayList<S_D_RV> s_d_rvArrayList;
    private Context context;

    public Adapter_D_RV(Context context, ArrayList<S_D_RV> s_d_rvArrayList) {
        this.context = context;
        this.s_d_rvArrayList = s_d_rvArrayList;
    }

    @NonNull
    @Override
    public Adapter_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_a_gv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ViewHolder holder, int position) {
        final S_D_RV s_d_rv = s_d_rvArrayList.get(position);
        if (s_d_rv == null) {
            return;
        }
        Glide.with(context).load(App.url + s_d_rv.getImgUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(100))).into(holder.image);
        holder.text.setText(s_d_rv.getName());
        holder.image.setOnClickListener(v -> {
            Intent intent = new Intent(context, LSLB.class);
            intent.putExtra("id",s_d_rv.getId());
            context.startActivity(intent);
        });
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        int w = context.getResources().getDisplayMetrics().widthPixels;
        params.width = w / 4;
        holder.itemView.setLayoutParams(params);

    }

    @Override
    public int getItemCount() {
        return s_d_rvArrayList.size();
    }

    public class Adapter_ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        public Adapter_ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
        }
    }
}
