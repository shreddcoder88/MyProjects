package com.jebi.jvhor_000.campusmap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jvhor_000 on 4/25/2017.
 */

public class DirectoryPage extends Fragment {


    public DirectoryPage() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.directory, container, false);


        return view;


    }
}
