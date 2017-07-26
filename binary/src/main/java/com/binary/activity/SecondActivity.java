package com.binary.activity;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.binary.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bestotem on 2017/7/12.
 */

public class SecondActivity extends AppCompatActivity {
    public static final String TAG = "SecondActivity";

    private Button btn1, btn2;
    private TextView tvShow;
    private ImageView imgView;

    private static String imgUrl = "http://img.hb.aicdn.com/1663bc03df1927e714f58ae6fcba20b2ca1afaad2a6ee1-vxwDzm_fw658";
    private ProgressDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn1 = (Button) findViewById(R.id.btn_post);
        btn2 = (Button) findViewById(R.id.btn_PostAtTime);
        tvShow = (TextView) findViewById(R.id.tv_show);
        imgView = (ImageView) findViewById(R.id.iv_Load);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Load Hint");
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(runnable).start();
                dialog.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tvShow.setText("at postDelayed 3 Second");
                            }
                        }, 3000);
                    }
                }).start();
            }
        });
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(imgUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5 * 1000);
                connection.setUseCaches(true);
                connection.setRequestMethod("GET");
                connection.connect();

                if (connection.getResponseCode() == 200) {
                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = bitmap;

                    handler.sendMessage(msg);

                    inputStream.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    };


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Bitmap bmp = (Bitmap) msg.obj;
                    imgView.setImageBitmap(bmp);
                    dialog.dismiss();
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dialog.dismiss();
        this.finish();
    }
}
