package com.pparreno.ligchat.chatroom.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pparreno.ligchat.chatroom.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatRoomRepository {

    private DatabaseReference messagesRef;

    public MutableLiveData<Message> messageMutableLiveData;

    public ChatRoomRepository() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.messageMutableLiveData = new MutableLiveData<>();
        this.messagesRef = firebaseDatabase.getReferenceFromUrl("https://ligchat-d8920.firebaseio.com/room/1/messages");
        messagesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message newMessage = snapshot.getValue(Message.class);
                messageMutableLiveData.postValue(newMessage);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void sendChatMessage(@NonNull Message message) {
        if(messagesRef != null)
        {
            messagesRef.push().setValue(message);
        }
    }


}
