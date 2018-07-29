package com.xdx.textviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import widget.marqueeview.MarqueeCleanClickListener;
import widget.marqueeview.MarqueeTextView;
import widget.marqueeview.MarqueeTextViewClickListener;

public class TestActivity extends AppCompatActivity {

    private MarqueeTextView marqueeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("result","ac  挂了");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("result","ac  onPause");
    }
}
