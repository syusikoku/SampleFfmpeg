package com.kasaax.commons.adapter;


import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class NewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<? extends Fragment> mList;

    private String[] titles = {"四季红", "花里红", "光月", "星野", "天运"};

    public NewPagerAdapter(FragmentManager fm, List<? extends Fragment> _list) {
        super(fm);
        this.mList = _list;
    }

    @Override
    public Fragment getItem(int _i) {
        return mList.get(_i);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}