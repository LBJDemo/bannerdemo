package com.xdx.textviewdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xdx.textviewdemo.marqueeview.MarqueeCleanClickListener;
import com.xdx.textviewdemo.marqueeview.MarqueeTextView;
import com.xdx.textviewdemo.marqueeview.MarqueeTextViewClickListener;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class MainActivity extends AppCompatActivity {
    private List<String> bannerList = new ArrayList<>();
    private MarqueeTextView marqueeTextView;
    private SharedPreferences sharedPreferences;
    private Boolean first_run;
    private BGABanner mBannerGuideContent;

    public static final String[] IMAGES = {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526921235090&di=792f691f520d6e69010e79ca0a480a6a&imgtype=0&src=http%3A%2F%2Ffile3.youboy.com%2Fd%2F168%2F85%2F98%2F1%2F594351s.jpg"
            , "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526921321244&di=9f2dc0ef61aaf38878f0af2adbbe7f23&imgtype=0&src=http%3A%2F%2Fimg1.sooshong.com%2Fpics%2F201703%2F8%2F20173810101927.jpg"

            , "http://cdnq.duitang.com/uploads/blog/201309/16/20130916142913_tkxNZ.jpeg"
            , "http://img5.imgtn.bdimg.com/it/u=768245655,2207102486&fm=21&gp=0.jpg", "http://img5.imgtn.bdimg.com/it/u=893152607,157679322&fm=21&gp=0.jpg"
            , "http://cdnq.duitang.com/uploads/blog/201309/16/20130916142913_tkxNZ.jpeg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        marqueeTextView = findViewById(R.id.mall_view_home_marquee);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TestActivity.class));
            }
        });
        set();
         sharedPreferences = getSharedPreferences("isKill",0);
         first_run = sharedPreferences.getBoolean("Kill",true);


         initBanner();
         initData();
    }

    public void initBanner() {
        bannerList.clear();
        for (int i = 0; i < 3; i++) {
            bannerList.add(IMAGES[i]);
        }
        mBannerGuideContent = (BGABanner) findViewById(R.id.banner_guide_content);//banner图
        mBannerGuideContent.setData(bannerList, null);
    }


    /**
     * 初始化轮播图
     * thumbnail(0.1f)加载缩略图
     * dontAnimate 不加载默认动画
     */
    public void initData() {
        mBannerGuideContent.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                    Glide.with(MainActivity.this).load(model).
                            placeholder(R.color.color_9c9c9c).error(R.color.color_9c9c9c).
                            dontAnimate().thumbnail(0.1f).into((ImageView) view);

            }
        });
        mBannerGuideContent.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                Toast.makeText(banner.getContext(), "点击了第" + position+"张图片", Toast.LENGTH_SHORT).show();
            }
        });

    }



    /**
     * fsdfsdf
     */
    private void set() {

        marqueeTextView.initMarqueeTextView(new MarqueeTextViewClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(getApplicationContext(),"点击了文字",Toast.LENGTH_SHORT).show();
            }
        });
        marqueeTextView.setCleanClickListener(new MarqueeCleanClickListener() {
            @Override
            public void onRight(View view, Drawable right) {
                Toast.makeText(getApplicationContext(),"点击了清除",Toast.LENGTH_SHORT).show();
                marqueeTextView.setVisibility(View.GONE);
                sharedPreferences.edit().putBoolean("Kill",false).commit();
            }
        });

    }

//    public int isBackground() {
//        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
//                .getRunningAppProcesses();
//        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
//            if (appProcess.processName.equals(getPackageName())) {
//                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
//                    //处于后台
//                    return isBackground();
//                } else {
//                    //处于前台
//                    return is;
//                }
//            }
//        }
//        //被杀了
//        return KILL;
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("result","挂了");
        sharedPreferences.edit().putBoolean("Kill",true).commit();
        sharedPreferences.edit().remove("Kill");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("result","onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (first_run){
            marqueeTextView.setVisibility(View.GONE);
        }
    }
}
