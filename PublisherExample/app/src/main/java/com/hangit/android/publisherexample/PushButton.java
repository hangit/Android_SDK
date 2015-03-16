package com.hangit.android.publisherexample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hangit.android.hangit_sdk.UIMainActivity;


public class PushButton extends ActionBarActivity {
    private static final String START_ACTIVITY_SOURCE = "EXAMPLE_PUSH_BUTTON";
    private Button launchHangITButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_button);

        launchHangITButton = (Button)findViewById(R.id.activity_push_button_launch_hangit_button);
        launchHangITButton.setOnClickListener(getLaunchHangITButtonClickListener());
    }

    private View.OnClickListener getLaunchHangITButtonClickListener() {
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(UIMainActivity.makeIntent(PushButton.this, UIMainActivity.class,
                        START_ACTIVITY_SOURCE));
            }
        };
    }
}
