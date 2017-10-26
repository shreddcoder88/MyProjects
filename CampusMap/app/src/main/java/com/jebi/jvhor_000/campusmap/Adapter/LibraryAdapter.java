package com.jebi.jvhor_000.campusmap.Adapter;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jebi.jvhor_000.campusmap.FloorPlan;
import com.jebi.jvhor_000.campusmap.MainPage;


public class LibraryAdapter extends FragmentStatePagerAdapter{



    private int page;

    public LibraryAdapter(FragmentManager fm, int position){
        super(fm);
        this.page = position;
    }

    @Override
    public Fragment getItem(int position) {



        switch (position){

            case 0:
                MainPage library = new MainPage().newInstance("Library");
                return library;
            case 1:
                FloorPlan fpSheet = new FloorPlan().newInstance("Library");
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