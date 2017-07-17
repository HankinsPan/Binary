package com.binary;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bestotem on 2017/7/12.
 */

public class SecondActivity extends AppCompatActivity {
    public static final String TAG = "SecondActivity";

    private Button btn1,btn2;
    private TextView tvShow;
    private ImageView imgView;

    private static Handler handler = new Handler();

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
                new Thread(new MyThread()).start();

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
                        },3000);
                    }
                }).start();
            }
        });
    }

    public class MyThread implements Runnable{

        @Override
        public void run() {
        }
    }
}
