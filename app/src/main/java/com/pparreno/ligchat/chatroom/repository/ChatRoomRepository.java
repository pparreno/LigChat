package com.pparreno.ligchat.chatroom.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pparreno.ligchat.chatroom.model.Message;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class ChatRoomRepository {

    private DatabaseReference messagesRef;

    public MutableLiveData<List<Message>> messagesMutableLiveData;
    public MutableLiveData<Message> messageMutableLiveData;
    public MutableLiveData<Boolean> shouldClearListFlagMLiveData;

    public ChatRoomRepository() {

        this.messagesMutableLiveData = new MutableLiveData<>();
        this.messageMutableLiveData = new MutableLiveData<>();
        this.shouldClearListFlagMLiveData = new MutableLiveData<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        this.messagesRef = firebaseDatabase.getReferenceFromUrl("https://ligchat-d8920.firebaseio.com/room/1/messages");
        messagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Timber.d("onDataChange");
                shouldClearListFlagMLiveData.postValue(true);
                Timber.d("Count %d", snapshot.getChildrenCount());
                ArrayList<Message> messages = new ArrayList<>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Message message = postSnapshot.getValue(Message.class);
                    Timber.d("Email %s", message.getOwner().getEmail());
                    messages.add(message);
                }
                messagesMutableLiveData.postValue(messages);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        messagesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Timber.d("onChildAdded");
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
        if (messagesRef != null) {
            messagesRef.push().setValue(message);
        }
    }


}
