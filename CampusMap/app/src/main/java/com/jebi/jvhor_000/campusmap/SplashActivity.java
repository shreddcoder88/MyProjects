package com.jebi.jvhor_000.campusmap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jvhor_000 on 4/11/2017.
 */

public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        finish();

    }

}
