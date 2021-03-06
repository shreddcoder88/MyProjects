package com.jebi.jvhor_000.campusmap.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.jebi.jvhor_000.campusmap.FloorPlan;
import com.jebi.jvhor_000.campusmap.MainPage;

/**
 * Created by jvhor_000 on 5/14/2017.
 */

public class FarnumAdapter extends FragmentStatePagerAdapter {

    private int page;

    public FarnumAdapter(FragmentManager fm, int position) {
        super(fm);
        this.page = position;
    }

    @Override
    public Fragment getItem(int position){

        switch (position){

            case 0:
                MainPage farnum = new MainPage().newInstance("Farnum");
                return farnum;
            case 1:
                FloorPlan fpsheet = new FloorPlan().newInstance("Farnum");
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
