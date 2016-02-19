package com.qianfeng.kun.yuwei.other.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by liukun
 *
 * @date : 2016/2/15.12:08.
 */
public class GuideAdapter extends PagerAdapter {

    private List<ImageView> imageViewList;

    public GuideAdapter(List<ImageView> imageViewList) {
        this.imageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=imageViewList.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView imageView=imageViewList.get(position);
        container.removeView(imageView);

    }
}
