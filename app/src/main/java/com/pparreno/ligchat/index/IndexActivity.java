package com.pparreno.ligchat.index;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pparreno.ligchat.R;
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
        startActivity(intent);
    }

    public void onSignInpButtonClick(@NonNull View v){

    }
}