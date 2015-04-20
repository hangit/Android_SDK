package com.hangit.android.publisherexample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hangit.android.hangit_sdk.HangITBoundingBox;
import com.hangit.android.hangit_sdk.HangITClient;
import com.hangit.android.hangit_sdk.ManagerGeneral;
import com.hangit.android.hangit_sdk.ModelBoundingBoxResponse;

/**
 * The BackgroundOnly activity demonstrates how the HangIT SDK can be used to run in the background
 * of the main application.
 */
public class BackgroundOnly extends ActionBarActivity implements HangITClient.HangITClientEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_only);

        //We must first define a listener to receive callback events from the HangIT SDK
        ManagerGeneral.getHangITClient().addEventListener(this);

        //To start the HangIT SDK service we all the initialize method.
        //hangit_sdk_key must
        ManagerGeneral.getHangITClient().initialize(this, getString(R.string.hangit_sdk_key));
    }

    @Override
    protected void onPause() {
        super.onPause();
        ManagerGeneral.getHangITClient().onPause(this);
    }

    /**
     * This callback is fired when the device encounters a place.
     * @param hangITBoundingBoxes An array of boxes encountered.
     */
    @Override
    public void onPlaceEncountered(HangITBoundingBox[] hangITBoundingBoxes) {
        Toast.makeText(this, "A place was encountered.", Toast.LENGTH_SHORT).show();
    }

    /**
     * This callback is fired when bounding boxes are received from the HangIT service.
     * @param modelBoundingBoxResponse A model object containing our bounding box information.
     */
    @Override
    public void onBoundingBoxChange(ModelBoundingBoxResponse modelBoundingBoxResponse) {
        Toast.makeText(this, "HangIT is running", Toast.LENGTH_SHORT).show();
    }

    /**
     * This call back is fired when there is a network failure when attempting to communicate
     * with the HangIT server or when an invalid SDK key is provided.
     * @param s A string message with additional information on the failure.
     */
    @Override
    public void onBoundingBoxNetworkFailure(String s) {
        Toast.makeText(this, "A network failure occurred. Message : " + s, Toast.LENGTH_SHORT).show();
    }

    /**
     * This call back is fired when the HangITClient has a non network failure failure event.
     * @param i
     * @param i2
     */
    @Override
    public void onHangITClientFailureEvent(int i, int i2) {
        Toast.makeText(this, "A HangIT client failure event occurred.", Toast.LENGTH_SHORT).show();
    }
}
