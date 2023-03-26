package com.example.myapplication4.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication4.R;

public class ZHJG extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private TextView clgl;
    private TextView ljyy;
    private TextView wdyy;
    private TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhjg);
        initView();
        header.setText("预约检车");
        back.setOnClickListener(v -> {
            finish();
        });
        //跳转
        TZ();
        text.setText(Html.fromHtml("<p style=\\\\\\\"color:#191919;font-family:&amp;font-size:100%;font\u0002style:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;t\n" +
                "ext-indent:0px;\\\\\\\"><span style=\\\\\\\"font-size:16px;\\\\\\\">1.</span><span style=\\\\\\\"fo\n" +
                "nt-size:16px;\\\\\\\">至少提前两周预约</span><span style=\\\\\\\"font-size:16px;\\\\\\\">，\n" +
                "<span style=\\\\\\\"font-size:100%;\\\\\\\">预</span>约确定以支付定金为准，定金 20 元(不可退)；拍\n" +
                "照排期以收到定金顺序为准。\n" +
                "</span></p><span style=\\\\\\\"background-color:#FFFFFF;color:#191919;font-family:&quot;\n" +
                "font-size:16px;font-style:normal;font-weight:400;line-height:1.9;text-decoration:no\n" +
                "ne;\\\\\\\"></span><p style=\\\\\\\"color:#191919;font-family:&amp;font-size:100%;font-styl\n" +
                "e:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;text\u0002indent:0px;\\\\\\\"><span style=\\\\\\\"font-size:16px;\\\\\\\">2.检车余款于拍照当日结清：支付宝／\n" +
                "微信／现金均可。\n" +
                "</span></p><span style=\\\\\\\"background-color:#FFFFFF;color:#191919;font-family:&quot;\n" +
                "font-size:16px;font-style:normal;font-weight:400;line-height:1.9;text-decoration:no\n" +
                "ne;\\\\\\\"> </span><p style=\\\\\\\"color:#191919;font-family:&amp;font-size:100%;font-sty\n" +
                "le:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;text\n" +
                "-indent:0px;\\\\\\\"><span style=\\\\\\\"font-size:16px;\\\\\\\">3.如遇个人原因临时变更，请于原定拍\n" +
                "摄时间提前<span style=\\\\\\\"font-size:100%;\\\\\\\">至少 48 小时</span>与我联系更改，感谢理解；\n" +
                "预约当天无特殊理由取消，订单作废，再次预约重付 20 元定金。</span></p>"));
    }


    private void TZ() {
        clgl.setOnClickListener(v -> {
            startActivity(new Intent(ZHJG.this, CLGL.class));
        });
        ljyy.setOnClickListener(v -> {
            startActivity(new Intent(ZHJG.this, LJYY.class));
        });
        wdyy.setOnClickListener(v -> {
            startActivity(new Intent(ZHJG.this, WDYY.class));
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        clgl = (TextView) findViewById(R.id.clgl);
        ljyy = (TextView) findViewById(R.id.ljyy);
        wdyy = (TextView) findViewById(R.id.wdyy);
        text = (TextView) findViewById(R.id.text);
    }
}
