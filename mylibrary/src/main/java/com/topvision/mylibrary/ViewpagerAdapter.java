package com.topvision.mylibrary;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucy on 2017/8/7.
 */

public class ViewpagerAdapter extends PagerAdapter {
    private static final int PAGE_ITEM = 4;
    private Context mContext;
    private List<ItemBean> mList;
    private CustomViewpager viewPager;
    private List<GridAdapter> gridAdapterList = new ArrayList<>();
    public ViewpagerAdapter(Context context, List<ItemBean> list, CustomViewpager viewPager) {
        mContext = context;
        mList = list;
        this.viewPager = viewPager;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            int i = mList.size() / PAGE_ITEM;
            if (mList != null) {
                if (mList.size() % PAGE_ITEM == 0) {
                    return i;
                } else {
                    return i + 1;
                }
            }
        }
        return 0;
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.base_select_viewpager, null);
        CustomGridView gridView = (CustomGridView) view.findViewById(R.id.item_grid);
        //取4个
        GridAdapter adapter = new GridAdapter(mContext, getCurrentList(position), gridView);
        if (gridAdapterList.size() <= position) {
            gridAdapterList.add(adapter);
        }
        gridView.setAdapter(adapter);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));
    }

    public List<ItemBean> getCurrentList(int position) {
        List<ItemBean> list = new ArrayList<>();
        int start = position * PAGE_ITEM;
        int end = start + PAGE_ITEM;
        if (end > mList.size())
            end = mList.size();
        for (int i = start; i < end; i++) {
            list.add(mList.get(i));
        }
        return list;
    }

    public void doPreSelect() {
        int currentItem = viewPager.getCurrentItem();
        GridAdapter gridAdapter = gridAdapterList.get(currentItem);
        if (gridAdapter.currentPosition == 0 && currentItem != 0) {
            //上一页
            viewPager.setCurrentItem(--currentItem);
            doPrePage();
        } else {
            gridAdapter.setPreSelectItem();
        }
    }

    public void doNextSelect() {
        int currentItem = viewPager.getCurrentItem();
        GridAdapter gridAdapter = gridAdapterList.get(currentItem);
        if (gridAdapter.currentPosition == (PAGE_ITEM - 1) && currentItem != getCount() - 1) {
            //下一页
            viewPager.setCurrentItem(++currentItem);
            doNextPage();
        } else {
            gridAdapter.setNextSelectItem();
        }
    }

    private void doPrePage() {
        int currentItem = viewPager.getCurrentItem();
        gridAdapterList.get(currentItem).prePageSelectItem();
    }

    private void doNextPage() {
        int currentItem = viewPager.getCurrentItem();
        gridAdapterList.get(currentItem).NextPageSelectItem();
    }

    public int doClickSelect() {
        int currentItem = viewPager.getCurrentItem();
        GridAdapter gridAdapter = gridAdapterList.get(currentItem);
        //TODO 点击事件
        return gridAdapter.currentPosition;
    }
}
