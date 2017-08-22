package com.topvision.mylibrary;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by lucy on 2017/8/9.
 */

public class CustomViewpager extends ViewPager {
    public CustomViewpager(Context context) {
        super(context);
    }

    public CustomViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    private boolean isCanScroll = true;
//    public void setScanScroll(boolean isCanScroll) {
//        this.isCanScroll = isCanScroll;
//    }
//
//    @Override
//    public void scrollTo(int x, int y) {
//        if (isCanScroll) {
//            super.scrollTo(x, y);
//        }
//    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;//不要拦截事件   事件要分发给后面的控件
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;//不自己去处理事件
    }
}