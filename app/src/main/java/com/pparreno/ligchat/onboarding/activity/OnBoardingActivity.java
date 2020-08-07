package com.pparreno.ligchat.onboarding.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pparreno.ligchat.R;
import com.pparreno.ligchat.onboarding.fragment.SignUpFragment;

public class OnBoardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        getSupportActionBar().hide();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SignUpFragment.newInstance())
                    .commitNow();
        }
    }
}