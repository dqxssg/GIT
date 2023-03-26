package com.example.myapplication2;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication2.ui.FZC;
import com.example.myapplication2.ui.L;
import com.example.myapplication2.util.App;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {
    private List<TopicBean> mData;
    private Context mContext;
    private int columnCount = 5;

    public TopicAdapter(Context context, List<TopicBean> data) {
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_a_gridview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, final int position) {
        final TopicBean item = mData.get(position);
        if (item == null) {
            return;
        }
        Glide.with(mContext)
                .load(App.url + item.getImgUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(holder.image);
        holder.text.setText(item.getName());
        holder.line.setOnClickListener(v -> {
            mContext.startActivity(new Intent(mContext, FZC.class));
        });
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        int screenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        params.width = screenWidth / columnCount;
        holder.itemView.setLayoutParams(params);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        LinearLayout line;

        public TopicViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.text);
            line = view.findViewById(R.id.line);
            image = view.findViewById(R.id.image);
        }
    }
}