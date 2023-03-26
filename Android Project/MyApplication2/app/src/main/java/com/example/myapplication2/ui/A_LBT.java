package com.example.myapplication2.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication2.R;

public class A_LBT extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView text;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_lbt);
        Bundle bundle = this.getIntent().getExtras();
        String url = bundle.getString("url");
        String name = bundle.getString("name");
        initView();
        header.setText("轮播图详情");
        back.setOnClickListener(v -> {
            finish();
        });
        runOnUiThread(() -> {
            Glide.with(A_LBT.this)
                    .load(url)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(image);
            text.setText(name);
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        text = (TextView) findViewById(R.id.text);
        image = (ImageView) findViewById(R.id.image);
    }
}
