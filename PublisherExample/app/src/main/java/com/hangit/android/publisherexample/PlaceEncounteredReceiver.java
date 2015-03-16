package com.hangit.android.publisherexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PlaceEncounteredReceiver extends BroadcastReceiver {
    public PlaceEncounteredReceiver() {}
    
    @Override
    public void onReceive(Context context, Intent intent) {
        String placeData = intent.getStringExtra("placedata");
    }
}
