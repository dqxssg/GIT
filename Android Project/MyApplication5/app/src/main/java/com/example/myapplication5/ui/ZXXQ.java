package com.example.myapplication5.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication5.R;
import com.example.myapplication5.util.App;
import com.example.myapplication5.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ZXXQ extends AppCompatActivity {
    private TextView back;
    private TextView header;
    private ImageView img;
    private TextView mc;
    private TextView flzc;
    private TextView zxrs;
    private TextView fwcs;
    private TextView slzt;
    private TextView wtlx;
    private TextView wtms;
    private TextView scdzp;
    private TextView lxdh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zxxq);
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        int Id = bundle.getInt("Id");
        System.out.println("456" + id);
        initView();
        back.setOnClickListener(v -> {
            finish();
        });
        header.setText("咨询详情");
        System.out.println("789" + id);
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/legal-advice/" + id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s=response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            runOnUiThread(() -> {
                                try {
                                    if (jsonObject1.getString("state").equals("0")) {
                                        slzt.setText("受理状态：未受理");
                                    } else {
                                        slzt.setText("受理状态：已受理");
                                    }
                                    wtlx.setText("问题类型：" + jsonObject1.getString("legalExpertiseName"));
                                    wtms.setText("问题描述：" + jsonObject1.getString("content"));
                                    scdzp.setText("上传照片：" + jsonObject1.getString("imageUrls"));
                                    lxdh.setText("联系电话：" + jsonObject1.getString("phone"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        new HttpUtil()
                .sendResurltToken("/prod-api/api/lawyer-consultation/lawyer/" + Id, "", "GET", new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String s = response.body().string();
                        System.out.println("456" + s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            runOnUiThread(() -> {
                                try {
                                    Glide.with(ZXXQ.this)
                                            .load(App.url + jsonObject1.getString("avatarUrl"))
                                            .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                                            .into(img);
                                    mc.setText(jsonObject1.getString("name"));
                                    flzc.setText("法律专长；" + jsonObject1.getString("legalExpertiseName"));
                                    zxrs.setText("咨询人数；" + jsonObject1.getString("serviceTimes"));
                                    fwcs.setText("服务人数；" + jsonObject1.getString("serviceTimes"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.back);
        header = (TextView) findViewById(R.id.header);
        img = (ImageView) findViewById(R.id.img);
        mc = (TextView) findViewById(R.id.mc);
        flzc = (TextView) findViewById(R.id.flzc);
        zxrs = (TextView) findViewById(R.id.zxrs);
        fwcs = (TextView) findViewById(R.id.fwcs);
        slzt = (TextView) findViewById(R.id.slzt);
        wtlx = (TextView) findViewById(R.id.wtlx);
        wtms = (TextView) findViewById(R.id.wtms);
        scdzp = (TextView) findViewById(R.id.scdzp);
        lxdh = (TextView) findViewById(R.id.lxdh);
    }
}

//        "id": 1760,
//        "fromUserId": 1117214,
//        "lawyerId": 10,
//        "legalExpertiseId": 7,
//        "content": "要把沙发装冰箱，总共分几步？",
//        "imageUrls": "/dev-api/profile/upload/image/2022/02/25/19d10a51-2950-46b0-ad70-daf7c5160320.jpg,/dev-api/profile/upload/image/2022/02/25/7dd5505a-8ffb-49d5-81e2-58a66f08d34a.png",
//        "phone": "18842656666",
//        "state": "0",
//        "score": 0,
//        "evaluate": null,
//        "lawyerName": "陈宇律师",
//        "legalExpertiseName": null,
//        "likeCount": 647
//        },
//        {
//        "searchValue": null,
//        "createBy": null,
//        "createTime": "2023-03-09 10:18:49",
//        "updateBy": null,
//        "updateTime": null,
//        "remark": null,
//        "params": {},
//        "id": 1767,
//        "fromUserId": 1117214,
//        "lawyerId": 10,
//        "legalExpertiseId": 10,
//        "content": "zdcfzsc",
//        "imageUrls": null,
//        "phone": "zxczxc",
//        "state": "0",
//        "score": 0,
//        "evaluate": null,
//        "lawyerName": "陈宇律师",
//        "legalExpertiseName": "拆迁安置",
//        "likeCount": 647
//        },
//        {
//        "searchValue": null,
//        "createBy": null,
//        "createTime": "2023-03-10 08:31:05",
//        "updateBy": null,
//        "updateTime": null,
//        "remark": null,
//        "params": {},
//        "id": 1792,
//        "fromUserId": 1117214,
//        "lawyerId": 10,
//        "legalExpertiseId": 7,
//        "content": "要把沙发装冰箱，总共分几步？",
//        "imageUrls": "/profile/upload/2023/03/10/44ab1c8e-52fe-47c3-b13b-989c7daa6f04.jpg",
//        "phone": "18842656666",
//        "state": "0",
//        "score": 0,
//        "evaluate": null,
//        "lawyerName": "陈宇律师",
//        "legalExpertiseName": null,
//        "likeCount": 647
//        },
//        {
//        "searchValue": null,
//        "createBy": null,
//        "createTime": "2023-03-10 10:52:30",
//        "updateBy": null,
//        "updateTime": null,
//        "remark": null,
//        "params": {},
//        "id": 1795,
//        "fromUserId": 1117214,
//        "lawyerId": 31,
//        "legalExpertiseId": 31,
//        "content": "22",
//        "imageUrls": null,
//        "phone": "11",
//        "state": "0",
//        "score": 0,
//        "evaluate": null,
//        "lawyerName": "王玉珏律师",
//        "legalExpertiseName": null,
//        "likeCount": 647
//        },
//        {
//        "searchValue": null,
//        "createBy": null,
//        "createTime": "2023-03-10 11:18:16",
//        "updateBy": null,
//        "updateTime": null,
//        "remark": null,
//        "params": {},
//        "id": 1796,
//        "fromUserId": 1117214,
//        "lawyerId": 10,
//        "legalExpertiseId": 7,
//        "content": "",
//        "imageUrls": null,
//        "phone": "",
//        "state": "0",
//        "score": 0,
//        "evaluate": null,
//        "lawyerName": "陈宇律师",
//        "legalExpertiseName": null,
//        "likeCount": 647
//        }
//        ],
//        "code": 200,
//        "msg": "查询成功"
//        }
