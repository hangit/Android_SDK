package com.hangit.android.publisherexample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


/**
 * The NotificationCallBack activity demonstrates how an application might customize the
 * down press event of a HangIT initiated notification.
 */
public class NotificationCallback extends ActionBarActivity {

    TextView campaignName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_callback);

        campaignName = (TextView)findViewById(R.id.campaign_name);

        Intent intent = getIntent();

        campaignName.setText(intent.getStringExtra("com.hangit.android.hangit_sdk.KEY_OFFER_DISPLAY_MESSAGE"));
    }
}
