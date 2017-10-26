package com.jebi.jvhor_000.campusmap;


import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SettingsActivity extends ListActivity  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        ListView listView = (ListView) findViewById(R.id.settings_list);
        ArrayAdapter listAdapter = new ArrayAdapter<>(this, R.layout.settings_listview_adapter, R.id.menu_text, getResources().getStringArray(R.array.list_settings));
        listView.setAdapter(listAdapter);


    }

}

