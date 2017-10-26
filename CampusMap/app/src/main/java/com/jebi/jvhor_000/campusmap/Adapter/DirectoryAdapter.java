package com.jebi.jvhor_000.campusmap.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jebi.jvhor_000.campusmap.MainPage;

/**
 * Created by jvhor_000 on 5/18/2017.
 */

public class DirectoryAdapter extends FragmentStatePagerAdapter {
    int page;

    public DirectoryAdapter(FragmentManager fm, int position) {
        super(fm);
        this.page = position;
    }

    @Override
    public Fragment getItem(int position) {


        return new MainPage();
    }

    @Override
    public int getCount() {
        return page;
    }


}
