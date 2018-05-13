package com.yckj.baselib.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpc on 2016/11/18.
 */
public class FragmentsPagerAdapter extends FragmentStatePagerAdapter {

    public final List<Fragment> mFragments = new ArrayList<>();
    public final List<String> mFragmentTitles = new ArrayList<>();


    public FragmentsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    public Fragment getFragmentByTitle(String title) {
        int i = mFragmentTitles.indexOf(title);
        return mFragments.get(i);
    }



}