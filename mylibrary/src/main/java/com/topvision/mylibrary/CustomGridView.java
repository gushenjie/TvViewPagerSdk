package com.topvision.mylibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by lucy on 2017/8/9.
 */

public class CustomGridView extends GridView {
    public CustomGridView(Context context) {
        super(context);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //        return scrollable && super.onInterceptTouchEvent(ev);
        return false;//不要拦截事件   事件要分发给后面的控件
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //        return scrollable && super.onTouchEvent(ev);
        return false;//不自己去处理事件
    }
}
