package com.example.localyticsplugindev;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import org.apache.cordova.*;

import com.localytics.android.*;

public class Main extends DroidGap {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
