package com.jebi.jvhor_000.campusmap.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jebi.jvhor_000.campusmap.EventsPage;

public class EventsAdapter extends FragmentStatePagerAdapter {

    int page;

    public EventsAdapter(FragmentManager fm, int position){
        super(fm);
        this.page = position;

    }


    @Override
    public Fragment getItem(int position) {

        EventsPage eventsPage = new EventsPage();
        return eventsPage;

        /*switch (position) {

            case 0:
                SweeneyPage sweeneySheet = new SweeneyPage();
                return sweeneySheet;
            case 1:
                FarnumPage farnumSheet = new FarnumPage();
                return farnumSheet;
            case 2:
                LittleHallPage littleHallSheet = new LittleHallPage();
                return littleHallSheet;
            case 3:
                Library librarySheet = new Library();
                return librarySheet;
            case 4:
                McauliffePage mcauliffePage = new McauliffePage();
                return mcauliffePage;
            case 5:
                DirectoryPage directoryPage = new DirectoryPage();
                return directoryPage;
            case 6:
                GrapponePage grapponePage = new GrapponePage();
                return grapponePage;
            case 7:
                MacruryPage macruryPage = new MacruryPage();
                return macruryPage;
            default:
                EventsPage eventsPage = new EventsPage();
                return eventsPage;

        }*/
    }

    @Override
    public int getCount() {
        return page;
    }
}
