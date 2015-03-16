package com.hangit.android.publisherexample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hangit.android.hangit_sdk.HangITBoundingBox;
import com.hangit.android.hangit_sdk.HangITClient;
import com.hangit.android.hangit_sdk.ManagerGeneral;
import com.hangit.android.hangit_sdk.ModelBoundingBoxResponse;


public class ClearUser extends ActionBarActivity implements HangITClient.HangITClientEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_user);

        ManagerGeneral.getHangITClient().addEventListener(this);
        ManagerGeneral.getHangITClient().initialize(this, getString(R.string.hangit_sdk_key));
        ManagerGeneral.getHangITClient().clearUser(this, null);
    }

    @Override
    public void onBoundingBoxNetworkFailure(String s) {

    }

    @Override
    public void onBoundingBoxChange(ModelBoundingBoxResponse modelBoundingBoxResponse) {

    }

    @Override
    public void onHangITClientFailureEvent(int i, int i2) {

    }

    @Override
    public void onPlaceEncountered(HangITBoundingBox[] hangITBoundingBoxes) {

    }
}
