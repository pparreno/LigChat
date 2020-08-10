package com.pparreno.ligchat.chatroom.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pparreno.ligchat.R;
import com.pparreno.ligchat.chatroom.ui.fragment.ChatRoomFragment;

public class ChatRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room_activity);
        getSupportActionBar().hide();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ChatRoomFragment.newInstance())
                    .commitNow();
        }
    }
}