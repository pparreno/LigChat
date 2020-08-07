package com.pparreno.ligchat.index;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pparreno.ligchat.R;
import com.pparreno.ligchat.constants.OnBoardingType;
import com.pparreno.ligchat.onboarding.activity.OnBoardingActivity;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        getSupportActionBar().hide();
    }

    public void onSignUpButtonClick(@NonNull View v){
        Intent intent = new Intent(this, OnBoardingActivity.class);
        intent.putExtra(OnBoardingActivity.KEY_ON_BOARDING_TYPE, OnBoardingType.SIGN_UP);
        startActivity(intent);
    }

    public void onSignInpButtonClick(@NonNull View v){
        Intent intent = new Intent(this, OnBoardingActivity.class);
        intent.putExtra(OnBoardingActivity.KEY_ON_BOARDING_TYPE, OnBoardingType.SIGN_IN);
        startActivity(intent);
    }
}