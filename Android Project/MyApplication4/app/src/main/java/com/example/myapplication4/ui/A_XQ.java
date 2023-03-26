package com.example.myapplication4.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication4.R;

public class A_XQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private ImageView image;
    private TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_xq);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        String img = bundle.getString("img");
        initView();
        header.setText("详情");
        back.setOnClickListener(v -> {
            finish();
        });
        Glide.with(A_XQ.this)
                .load(img)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(image);
        text.setText(name);
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
    }
}
