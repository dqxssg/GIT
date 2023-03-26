package com.example.myapplication3.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication3.R;

public class A_RMZT extends AppCompatActivity {
    private ImageView back;
    private TextView header;
    private ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_rmzt);
        Bundle bundle = this.getIntent().getExtras();
        String image = bundle.getString("img");
        initView();
        header.setText("热门主题");
        back.setOnClickListener(v -> {
            finish();
        });
        Glide.with(A_RMZT.this)
                .load(image)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(img);
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        img = (ImageView) findViewById(R.id.img);
    }
}
