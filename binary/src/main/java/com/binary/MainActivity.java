package com.binary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.binary.compent.RecycActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private ImageView imageView;
    private Button btnOne, btnTwo,btnFour, btnDrawC, btnDrawR,btnDrawL,btnRecyc;
    private ProgressBar progressBar;

    private RelativeLayout mRlaImage;
    private LinearLayout mRlaDraw;
    private RelativeLayout mRlaDrawCircle;
    private RelativeLayout mRlaDrawRect;


    private int num = 0;
    List<String> imageUrl;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        handler = new MyHandler();
//        final DrawRect drawRect = (DrawRect) findViewById(R.id.draw_line);
//        drawRect.startAnim();


        initView();
        addListener();
    }


    private void initView() {
        imageView = (ImageView) findViewById(R.id.iv_bg);
        btnOne = (Button) findViewById(R.id.btn_show);
        btnTwo = (Button) findViewById(R.id.btn_turn);
        btnFour = (Button) findViewById(R.id.btn_list);

        btnDrawC = (Button) findViewById(R.id.btn_drawC);
        btnDrawR = (Button) findViewById(R.id.btn_drawR);
        btnDrawL = (Button) findViewById(R.id.btn_reList);

        btnRecyc = (Button) findViewById(R.id.btn_recyc);

        progressBar = (ProgressBar) findViewById(R.id.pro_bar);

        mRlaImage = (RelativeLayout) findViewById(R.id.rla_Img);
        mRlaDraw = (LinearLayout) findViewById(R.id.rla_draw);
        mRlaDrawCircle = (RelativeLayout) findViewById(R.id.rla_drawCircle);
        mRlaDrawRect = (RelativeLayout) findViewById(R.id.rla_drawRect);


        imageUrl = new ArrayList<String>();
        imageUrl.add("http://img.hb.aicdn.com/3fb58311847342c5825fc12141a6b3c3c10af245182a5-bL7uDr_fw658");
        imageUrl.add("http://img.hb.aicdn.com/179eb10cf63c2decd22309c0f33cc19f30d0385e6cf64-PrQPDL_fw658");
        imageUrl.add("http://img.hb.aicdn.com/06295b887dec873fef9366e556a0dc8d8da0847fef6ff-4yxFl6_fw658");
        imageUrl.add("http://img.hb.aicdn.com/ecf299e3dde60a9a478553274d76c05b3fa66d1fdeea-Yr7W3d_fw658");
        imageUrl.add("http://img.hb.aicdn.com/1663bc03df1927e714f58ae6fcba20b2ca1afaad2a6ee1-vxwDzm_fw658");

    }

    private void addListener() {
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRlaDraw.setVisibility(View.GONE);
                mRlaImage.setVisibility(View.VISIBLE);

                num++;
                MyAsyncTask myAsyncTask = new MyAsyncTask(getApplicationContext());
                myAsyncTask.execute(imageUrl.get(num % imageUrl.size()));

            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FourActivity.class);
                startActivity(intent);
            }
        });

        btnDrawC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mRlaImage.setVisibility(View.GONE);
//                mRlaDraw.setVisibility(View.VISIBLE);
//                mRlaDrawCircle.addView(new DrawView(MainActivity.this));

                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        btnDrawR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mRlaImage.setVisibility(View.GONE);
//                mRlaDraw.setVisibility(View.VISIBLE);
//                mRlaDraw.setVisibility(View.GONE);
//                mRlaDrawRect.addView(new DrawRect(MainActivity.this));

                Intent intent = new Intent(MainActivity.this,SixActivity.class);
                startActivity(intent);



            }
        });

        btnDrawL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FiveActivity.class);
                startActivity(intent);
            }
        });

        btnRecyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecycActivity.class);
                startActivity(intent);
            }
        });

    }

    class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

        public MyAsyncTask(Context context) {
            progressBar.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
        }


        /**
         * 任务没有被线程池执行之前调用
         * 运行在【 UI 】线程中
         * 比如：等待下载进度的progress
         */
        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "<1> Work is Start", Toast.LENGTH_SHORT).show();
        }

        /**
         * 任务被线程池执行时调用
         * 运行在【 子 】线程中
         * 处理比较耗时的任务 不允许做任何UI更新操作
         * 比如：下载 网络连接
         *
         * @param strings
         * @return
         */
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;

            try {
                URL url = new URL(strings[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

//                Toast.makeText(getApplicationContext(), "send back Image", Toast.LENGTH_SHORT).show();

                Log.e(TAG, "doInBackground ->" + strings);

                Message msg = new Message();
                msg.what = 1;
                msg.obj = "send back Image";
                handler.sendMessage(msg);

                inputStream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }


        /**
         * 任务在正在执行中调用 Running
         * 回调给UI主线程进度
         * 比如：上传 下载的进度
         *
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.e(TAG, "values ->" + values);
            Toast.makeText(getApplicationContext(), "values ->" + values, Toast.LENGTH_SHORT).show();
        }

        /**
         * 对返回结果处理，更新UI（必写）
         * 任务在线程池中执行结束了 回调结果给UI主线程
         *
         * @param bitmap
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            progressBar.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                Toast.makeText(getApplicationContext(), "Net work is Error", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 任务关闭
         * @param bitmap
         */
        @Override
        protected void onCancelled(Bitmap bitmap) {
            super.onCancelled(bitmap);
        }
    }


    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String str = (String) msg.obj;
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
    }

}
