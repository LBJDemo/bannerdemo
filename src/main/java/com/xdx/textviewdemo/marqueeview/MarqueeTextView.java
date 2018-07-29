package com.xdx.textviewdemo.marqueeview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.xdx.textviewdemo.R;
import com.xdx.textviewdemo.utils.DisplayUtil;


//import static com.baojun.baselib.utils.TouchUtils.RIGHT;

/**
 * <p>文件描述：<p>
 * <p>作者：xdx<p>
 * <p>创建时间：2018/7/26 17:13<p>
 * <p>更改时间：2018/7/26 17:13<p>
 * <p>版本号：2.0<p>
 */
public class MarqueeTextView extends LinearLayout {

    private Context mContext;
    private ViewFlipper viewFlipper;
    private View marqueeTextView;
//    private List<NoticeEntity> list;
    private MarqueeTextViewClickListener marqueeTextViewClickListener;
    private MarqueeCleanClickListener marqueeCleanClickListener;

    public MarqueeTextView(Context context) {
        super(context);
        mContext = context;
        initBasicView();
//        TextView textView = findViewById(R.id.tv_clean);
//        textView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                marqueeCleanClickListener.onRight();
//            }
//        });
    }


    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initBasicView();
    }

    public void setTextArraysAndClickListener( MarqueeTextViewClickListener marqueeTextViewClickListener) {
//        this.list = list;
        this.marqueeTextViewClickListener = marqueeTextViewClickListener;
        initMarqueeTextView(marqueeTextViewClickListener);
    }

    public void setCleanClickListener(MarqueeCleanClickListener marqueeCleanClickListener){
        this.marqueeCleanClickListener = marqueeCleanClickListener;
//        initRightTextView();
    }

    public void initBasicView() {
        marqueeTextView = LayoutInflater.from(mContext).inflate(R.layout.marquee_layout, null);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(marqueeTextView, layoutParams);
        viewFlipper = (ViewFlipper) marqueeTextView.findViewById(R.id.viewFlipper);
//        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom));
//        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top));
        viewFlipper.startFlipping();
    }

    public void initMarqueeTextView( final MarqueeTextViewClickListener marqueeTextViewClickListener) {
        viewFlipper.removeAllViews();
            final TextView textView = new TextView(mContext);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setBackgroundColor(getResources().getColor(R.color.color_9c9c9c));
            textView.setTextColor(getResources().getColor(R.color.color_333333));
//            textView.setCompoundDrawables(getDrawableLeft(), null, null, null);
            textView.setCompoundDrawables(getDrawableLeft(), null, getDrawableRight(), null);
            textView.setCompoundDrawablePadding(DisplayUtil.dip2px(getContext(), 15));
            textView.setText("测试一次点击事件是否有效啊啊啊啊啊啊啊啊啊啊啊啊啊");
//            textView.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (marqueeTextViewClickListener != null) {
//                        marqueeTextViewClickListener.onClick(entity.getLink());
//                    }
//                }
//            });

            textView.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            performClick(); //// getCompoundDrawables获取是一个数组，数组0,1,2,3,对应着左，上，右，下 这4个位置的图片，如果没有就为null
                            Drawable drawableRight = textView.getCompoundDrawables()[2];
                            if (drawableRight != null && event.getRawX() >= (textView.getRight() - drawableRight.getBounds().width())) {
                                marqueeCleanClickListener.onRight(v, drawableRight);
                                return true;
                            } else {
                                marqueeTextViewClickListener.onClick();
                            }
                            break;
                    }
//                    return super.onTouchEvent(event);
                    return MarqueeTextView.super.onTouchEvent(event);
                }
            });
            textView.setPadding(DisplayUtil.dip2px(getContext(), 20), DisplayUtil.dip2px(getContext(), 10), DisplayUtil.dip2px(getContext(), 20), DisplayUtil.dip2px(getContext(), 10));
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            viewFlipper.addView(textView, lp);

//        }
//        if (list.size() < 2) {
//            viewFlipper.stopFlipping();
//        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private Drawable mDrawableLeft;
    private Drawable mDrawableRight;

    public Drawable getDrawableLeft() {
        if (mDrawableLeft == null) {
            mDrawableLeft = getResources().getDrawable(R.mipmap.ic_marquee_no);
            mDrawableLeft.setBounds(0, 0, mDrawableLeft.getMinimumWidth(), mDrawableLeft.getMinimumHeight());
        }
        return mDrawableLeft;
    }

    public Drawable getDrawableRight() {
        if (mDrawableRight == null) {
            mDrawableRight = getResources().getDrawable(R.mipmap.ic_launcher_round);
//            mDrawableRight = getResources().getDrawable(R.mipmap.ic_marquee_next);
            mDrawableRight.setBounds(0, 0, mDrawableRight.getMinimumWidth(), mDrawableRight.getMinimumHeight());

        }
        return mDrawableRight;
    }

//    public void initRightTextView(){
//        viewFlipper.removeAllViews();
//        final TextView textView1 = new TextView(mContext);
//        textView1.setGravity(Gravity.RIGHT);
//        textView1.setTextColor(getResources().getColor(R.color.theme_white_accent));
//        textView1.setText("×");
//        textView1.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                marqueeCleanClickListener.onRight();
//            }
//        });
//
//        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        viewFlipper.addView(textView1, lp);
//    }

    public void releaseResources() {
        if (marqueeTextView != null) {
            if (viewFlipper != null) {
                viewFlipper.stopFlipping();
                viewFlipper.removeAllViews();
                viewFlipper = null;
            }
            marqueeTextView = null;
        }
    }
}
