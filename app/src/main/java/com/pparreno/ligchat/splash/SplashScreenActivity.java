package com.pparreno.ligchat.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.pparreno.ligchat.R;
import com.pparreno.ligchat.chatroom.ui.activity.ChatRoomActivity;
import com.pparreno.ligchat.index.IndexActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Intent intent;
                if(FirebaseAuth.getInstance().getCurrentUser() != null)
                {
                    intent = new Intent(SplashScreenActivity.this, ChatRoomActivity.class);
                } else {
                    intent = new Intent(SplashScreenActivity.this, IndexActivity.class);
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, 2000);

    }
}