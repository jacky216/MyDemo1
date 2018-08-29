package com.cretin.www.wheelsurfdemo;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cretin.www.wheelsruflibrary.listener.RotateListener;
import com.cretin.www.wheelsruflibrary.view.WheelSurfView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    WheelSurfView wheelSurfView2;
    WheelSurfView wheelSurfView;
    Button btn ;
    RotateListener tls;

    //颜色
    Integer[] colors = new Integer[]{Color.parseColor("#74B3DA"), Color.parseColor("#FAC664")
            , Color.parseColor("#91D2DD"), Color.parseColor("#CC633A")
            , Color.parseColor("#4DA04E"), Color.parseColor("#F9BD4E")
            , Color.parseColor("#D0673D")};
    //文字
    String[] des = new String[]{"画舫体验券", "谢谢参与", "画舫半价券"
            , "谢谢参与", "西湖香包", "谢谢参与", "西湖茶包"};
    //图标
    List<Bitmap> mListBitmap = new ArrayList<>();
//        for ( int i = 0; i < colors.length; i++ ) {
//              mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.coupon));
//
//        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);   //应用运行时，保持屏幕高亮，不锁屏
        init();
    }

    public void init(){
        btn = (Button)findViewById(R.id.button);
        wheelSurfView = findViewById(R.id.wheelSurfView);
        //wheelSurfView2 = findViewById(R.id.wheelSurfView2);



//
//        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.coupon));
//        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.thanks));
//        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.couponhalf));
//        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.thanks));
//        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.sachet));
//        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.thanks));
//        mListBitmap.add(BitmapFactory.decodeResource(getResources(), R.mipmap.tea));
//        //主动旋转一下图片
//        mListBitmap = WheelSurfView.rotateBitmaps(mListBitmap);
//
//        //获取第三个视图
//        WheelSurfView.Builder build = new WheelSurfView.Builder()
//                .setmColors(colors)
//                .setmTextColor(0xFFFFFFFF)
//                .setmDeses(des)
//                .setmIcons(mListBitmap)
//                .setmHuanImgRes(R.mipmap.yuanhuan)
//                .setmGoImgRes(R.mipmap.node)
//                .setmType(1)
//                .setmTypeNum(7)
//                .setmMinTimes(10)
//                .build();
//        wheelSurfView2.setConfig(build);

        tls = new RotateListener() {
            @Override
            public void rotateEnd(int position, String des) {
                Toast.makeText(MainActivity.this, " " + des, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rotating(ValueAnimator valueAnimator) {

            }

            @Override
            public void rotateBefore(ImageView goImg) {
                //抽奖位置
                int position = new Random().nextInt(7) + 1;
                wheelSurfView2.startRotate(position);
            }
        };

        //添加滚动监听
        //wheelSurfView.setRotateListener(tls);



        /*暴力模式，只旋转图*/
        //获取第一个视图

        String[] des1 = new String[]{"画舫体验券", "西湖茶包", "请继续努力", "西湖香包",
                "要加油哦", "画舫半价券", "谢谢参与" };

        //添加滚动监听
        wheelSurfView.setRotateListener(new RotateListener() {
            @Override
            public void rotateEnd(int position, String des) {
                //文字 逆时针顺序命名
                String[] des1 = new String[]{"画舫体验券", "西湖茶包", "请继续努力", "西湖香包",
                        "要加油哦", "画舫半价券", "谢谢参与" };
                Toast.makeText(MainActivity.this,  "  "+des + des1[position-1], Toast.LENGTH_LONG).show();
            }

            @Override
            public void rotating(ValueAnimator valueAnimator) {

            }

            @Override
            public void rotateBefore(ImageView goImg) {
                int position = new Random().nextInt(7) + 1;
                wheelSurfView.startRotate(position);

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = new Random().nextInt(7) + 1;
                wheelSurfView.startRotate(position);
            }
        });
    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                //Toast("你按下中间键");

                int position = new Random().nextInt(7) + 1;
                wheelSurfView.startRotate(position);
                //Toast.makeText(MainActivity.this,  " 你按下中间键 ", Toast.LENGTH_SHORT).show();
                break;

            case KeyEvent.KEYCODE_DPAD_DOWN:
                //Toast("你按下下方向键");
                break;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                //Toast("你按下左方向键");
                break;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                //Toast("你按下右方向键");
                break;

            case KeyEvent.KEYCODE_DPAD_UP:
                //Toast("你按下上方向键");
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void Toast(String str){
        Toast.makeText(MainActivity.this,  "  "+str, Toast.LENGTH_SHORT).show();
    }

}

