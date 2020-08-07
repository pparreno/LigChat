package com.pparreno.ligchat.onboarding.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pparreno.ligchat.R;
import com.pparreno.ligchat.constants.OnBoardingType;
import com.pparreno.ligchat.onboarding.fragment.SignInFragment;
import com.pparreno.ligchat.onboarding.fragment.SignUpFragment;

import java.util.Objects;

public class OnBoardingActivity extends AppCompatActivity {


    public static final String KEY_ON_BOARDING_TYPE = "OnBoardingActivity.KEY_ON_BOARDING_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        getSupportActionBar().hide();
        if (savedInstanceState == null) {
            if (!(Objects.requireNonNull(getIntent().getExtras()).getInt(KEY_ON_BOARDING_TYPE) == OnBoardingType.SIGN_UP)) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, SignInFragment.newInstance())
                        .commitNow();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, SignUpFragment.newInstance())
                        .commitNow();
            }

        }
    }
}