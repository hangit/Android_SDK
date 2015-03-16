package com.hangit.android.publisherexample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hangit.android.hangit_sdk.HangITBoundingBox;
import com.hangit.android.hangit_sdk.HangITClient;
import com.hangit.android.hangit_sdk.ManagerGeneral;
import com.hangit.android.hangit_sdk.ModelBoundingBoxResponse;

public class LocationEventCallback extends ActionBarActivity  implements HangITClient.HangITClientEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_only);

        ManagerGeneral.getHangITClient().addEventListener(this);
        ManagerGeneral.getHangITClient().initialize(this, getString(R.string.hangit_sdk_key));
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
        Toast.makeText(this, "A place was encountered.", Toast.LENGTH_SHORT).show();
    }
}
