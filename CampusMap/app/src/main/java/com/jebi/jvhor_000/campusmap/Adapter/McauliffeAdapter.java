package com.jebi.jvhor_000.campusmap.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.jebi.jvhor_000.campusmap.MainPage;

/**
 * Created by jvhor_000 on 5/15/2017.
 */

public class McauliffeAdapter extends FragmentStatePagerAdapter {
    private int page;

    public McauliffeAdapter(FragmentManager fm, int position) {
        super(fm);
        this.page = position;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                MainPage page = new MainPage().newInstance("Mcauliffe");
                return page;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return page;
    }
}