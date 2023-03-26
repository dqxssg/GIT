package com.example.zhcs.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.zhcs.R;

public class F_A_XQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView text;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_a_xq);
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        String s = bundle.getString("s");
        initView();
        header.setText("详情");
        back.setOnClickListener(v -> {
            finish();
        });
        text.setText(name);
        Glide.with(F_A_XQ.this)
                .load(s)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(image);
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        text = (TextView) findViewById(R.id.text);
        image = (ImageView) findViewById(R.id.image);
    }
}
