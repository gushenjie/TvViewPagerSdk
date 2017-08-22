package com.topvision.mylibrary;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
//1. 依赖commomsdk
// 2.集成baseActivity
// 3. 实现Touchinterace
public abstract class BaseSelectActivity extends Activity {
    private static final int PAGE_ITEM = 4;
    private CustomViewpager viewPager;
    private ViewpagerAdapter adapter;

    /**
     * 分别设置图片的数组
     */
    protected abstract int[] setOnImageLoad();

    /**
     * 分别设置标题的数组
     */
    protected abstract String[] setOnTitleLoad();

    /**
     * 点击事件汇总处理
     */
    protected abstract void setOnItemClicked(int position);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_base_select);
        viewPager = (CustomViewpager) findViewById(R.id.viewpager);
        //初始化数据条目
        initData(setOnImageLoad(), setOnTitleLoad());
    }

    private List<ItemBean> listDatas = new ArrayList<>();

    protected void initData(int[] icons, String[] titles) {
        if (icons.length == titles.length) {
            for (int i = 0; i < icons.length; i++) {
                String title = titles[i];
                int icon = icons[i];
                ItemBean bean = new ItemBean();
                bean.icon = icon;
                bean.title = title;
                listDatas.add(bean);
            }
            adapter = new ViewpagerAdapter(this, listDatas, viewPager);
            viewPager.setAdapter(adapter);
        } else {
            throw new IllegalArgumentException("数据长度不一致");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // setTouchInterfaceListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // setTouchInterfaceListener(null);
    }

//    @Override
//    public void doForwardTouch() {
//        //向前选择
//        adapter.doNextSelect();
//    }
//
//    @Override
//    public void doBackwardTouch() {
//        //向后选择
//        adapter.doPreSelect();
//    }
//
//    @Override
//    public void doClickTouch() {
//        int currentItem = viewPager.getCurrentItem();
//        int gridPosition = adapter.doClickSelect();
//        setOnItemClicked(gridPosition + (PAGE_ITEM * currentItem));
//    }
//
//    @Override
//    public void doDoubleTouch() {
//
//    }
}
