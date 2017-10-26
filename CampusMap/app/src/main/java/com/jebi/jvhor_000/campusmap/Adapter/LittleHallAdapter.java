package com.jebi.jvhor_000.campusmap.Adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jebi.jvhor_000.campusmap.FloorPlan;
import com.jebi.jvhor_000.campusmap.MainPage;

/**
 * Created by jvhor_000 on 5/15/2017.
 */

public class LittleHallAdapter extends FragmentStatePagerAdapter {
    private int page;

    public LittleHallAdapter(FragmentManager fm, int position) {
        super(fm);
        this.page = position;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                MainPage littlehall = new MainPage().newInstance("Little Hall");
                return littlehall;
            case 1:
                FloorPlan fpsheet = new FloorPlan().newInstance("Little Hall");
                return fpsheet;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return page;
    }
}
