package com.jebi.jvhor_000.campusmap.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jebi.jvhor_000.campusmap.FloorPlan;
import com.jebi.jvhor_000.campusmap.MainPage;


public class SweeneyAdapter extends FragmentStatePagerAdapter {

    private int page;

    public SweeneyAdapter(FragmentManager fm, int position){
        super(fm);
        this.page = position;
    }

    @Override
    public Fragment getItem(int position) {



        switch (position){

            case 0:
                MainPage sweeney = new MainPage().newInstance("Sweeney");
                return sweeney;
            case 1:
                FloorPlan fpSheet = new FloorPlan().newInstance("Sweeney");
                return fpSheet;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return page;
    }


}
