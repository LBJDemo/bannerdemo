package com.xdx.textviewdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xdx.textviewdemo.marqueeview.MarqueeCleanClickListener;
import com.xdx.textviewdemo.marqueeview.MarqueeTextView;
import com.xdx.textviewdemo.marqueeview.MarqueeTextViewClickListener;


public class MainActivity extends AppCompatActivity {

    private MarqueeTextView marqueeTextView;
    private SharedPreferences sharedPreferences;
    private Boolean first_run;

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
