package com.jebi.jvhor_000.campusmap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CalendarView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

import static android.util.TypedValue.COMPLEX_UNIT_SHIFT;
import static android.util.TypedValue.COMPLEX_UNIT_SP;

/**
 * Page for Events
 */

public class EventsPage extends Fragment {

    //TODO Pull innerHTML from the website
    String[] feeds = {"Classes Starts today", "tomorrow", "next day", "whenever"};
    int ctr = 0;
    private Timer time;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.events, container, false);

        //CalendarView mCalendar = (CalendarView) view.findViewById(R.id.calendar);
        WebView eventsWebview = view.findViewById(R.id.events_webview);
        eventsWebview.loadUrl("https://www.nhti.edu/news-events");


        final TextSwitcher text = (TextSwitcher) view.findViewById(R.id.text_switcher);
        text.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(getContext());
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(COMPLEX_UNIT_SP, 20);
                tv.setText(feeds[ctr]);
                return tv;
            }
        });

        time = new Timer();
        try {
            time.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switch_text(text);
                        }
                    });

                }
            }, 0, 6000);
        }
        catch (IllegalStateException e){
            e.printStackTrace();
        }

        return view;

    }
    private void switch_text(TextSwitcher ts){

        ts.setText(feeds[ctr]);
        if (ctr == (feeds.length - 1)){
            ctr = 0;
        }
        else
            ctr++;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        time.cancel();
    }

}


