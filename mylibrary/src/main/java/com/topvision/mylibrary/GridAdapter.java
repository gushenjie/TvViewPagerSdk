package com.topvision.mylibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lucy on 2017/8/7.
 */

public class GridAdapter extends BaseAdapter {
    private static final int PAGE_ITEM = 4;
    private Context mContext;
    private List<ItemBean> mList;
    private GridView gridView;
    public int currentPosition;
    public GridAdapter(Context mContext, List<ItemBean> mList, GridView gridView) {
        this.mContext = mContext;
        this.mList = mList;
        this.gridView = gridView;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ItemBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.base_select_grid_item, parent,false);
        //gridview自适应
        int height = gridView.getHeight();
        GridView.LayoutParams params = new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                height / 2);
        convertView.setLayoutParams(params);
        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.layout);
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        ItemBean bean = mList.get(position);
        iv.setImageResource(bean.icon);
        tv.setText(bean.title);
        if (currentPosition == position) {
            layout.setSelected(true);
        } else {
            layout.setSelected(false);
        }
        return convertView;
    }

    public void setPreSelectItem() {
        if (currentPosition != 0) {
            currentPosition--;
            notifyDataSetChanged();
        }
    }

    public void setNextSelectItem() {
        if (currentPosition != mList.size() - 1) {
            currentPosition++;
            notifyDataSetChanged();
        }
    }

    public void prePageSelectItem() {
        currentPosition = PAGE_ITEM - 1;
        notifyDataSetChanged();
    }

    public void NextPageSelectItem() {
        currentPosition = 0;
        notifyDataSetChanged();
    }
}
