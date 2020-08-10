package com.pparreno.ligchat.onboarding.ui.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.pparreno.ligchat.R;
import com.pparreno.ligchat.constants.OnBoardingType;
import com.pparreno.ligchat.onboarding.ui.fragment.SignInFragment;
import com.pparreno.ligchat.onboarding.ui.fragment.SignUpFragment;

import java.util.Objects;

public class OnBoardingActivity extends AppCompatActivity implements SignInFragment.SignInFragmentListener, SignUpFragment.SignUpFragmentListener {


    public static final String KEY_ON_BOARDING_TYPE = "OnBoardingActivity.KEY_ON_BOARDING_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        getSupportActionBar().hide();
        if (savedInstanceState == null) {
            if (!(Objects.requireNonNull(getIntent().getExtras()).getInt(KEY_ON_BOARDING_TYPE) == OnBoardingType.SIGN_UP)) {
                replaceToSignUpFragment();
            } else {
                replaceToSignInFragment();
            }
        }
    }

    @Override
    public void onSignUpTextClicked() {
        clearFragmentStack();
        replaceToSignUpFragment();
    }

    @Override
    public void onSignInTextClicked() {
        clearFragmentStack();
        replaceToSignInFragment();
    }

    private void clearFragmentStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        //this will clear the back stack and displays no animation on the screen
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void replaceToSignUpFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SignUpFragment.newInstance())
                .commitNow();
    }

    private void replaceToSignInFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SignInFragment.newInstance())
                .commitNow();
    }
}

