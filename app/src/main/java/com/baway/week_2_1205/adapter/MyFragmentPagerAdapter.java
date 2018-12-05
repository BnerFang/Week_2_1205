package com.baway.week_2_1205.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * 作者:  方诗康
 * 描述:
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {


    private Context mContext;
    private List<Fragment> mFragments;

    public MyFragmentPagerAdapter(FragmentManager fm,Context context, List<Fragment> fragments) {
        super(fm);
        mContext = context;
        mFragments = fragments;
    }


    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
