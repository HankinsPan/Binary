package com.binary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bestotem on 2017/7/17.
 */

public class ThirdActivity extends AppCompatActivity {

    public static final String TAG = "ThirdActivity";
    private Button btnAdd;
    List<String> imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initView();
        initData();
    }

    private void initData() {
        imageUrl = new ArrayList<String>();
        imageUrl.add("http://img.hb.aicdn.com/3fb58311847342c5825fc12141a6b3c3c10af245182a5-bL7uDr_fw658");
        imageUrl.add("http://img.hb.aicdn.com/179eb10cf63c2decd22309c0f33cc19f30d0385e6cf64-PrQPDL_fw658");
        imageUrl.add("http://img.hb.aicdn.com/06295b887dec873fef9366e556a0dc8d8da0847fef6ff-4yxFl6_fw658");
        imageUrl.add("http://img.hb.aicdn.com/ecf299e3dde60a9a478553274d76c05b3fa66d1fdeea-Yr7W3d_fw658");
        imageUrl.add("http://img.hb.aicdn.com/1663bc03df1927e714f58ae6fcba20b2ca1afaad2a6ee1-vxwDzm_fw658");

    }

    private void initView() {
        btnAdd = (Button) findViewById(R.id.btn_add);
        final WaterFallLayout fallLayout = (WaterFallLayout) findViewById(R.id.water_fall);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView(fallLayout);
            }
        });
    }

    private void addView(WaterFallLayout fallLayout) {
        Log.e(TAG," >>> addView >>>");

        Random random = new Random();
        final Integer num = Math.abs(random.nextInt());
        WaterFallLayout.LayoutParams layoutParams = new WaterFallLayout.LayoutParams(
                WaterFallLayout.LayoutParams.WRAP_CONTENT,
                WaterFallLayout.LayoutParams.WRAP_CONTENT);

        final ImageView addImg = new ImageView(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                URL url = null;
                try {
                    url = new URL(imageUrl.get(num % imageUrl.size()));
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);

                    inputStream.close();
                    addImg.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },3000);

        addImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        fallLayout.addView(addImg, layoutParams);

        fallLayout.setOnItemClickListener(new WaterFallLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int index) {
                Toast.makeText(ThirdActivity.this, "item = " + index, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
