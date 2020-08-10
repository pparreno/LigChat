package com.pparreno.ligchat.chatroom.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pparreno.ligchat.chatroom.model.Message;
import com.pparreno.ligchat.chatroom.repository.ChatRoomRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ChatRoomViewModel extends ViewModel {

    public MutableLiveData<Message> messageMutableLiveData;
    private ChatRoomRepository chatRoomRepository;
    LifecycleOwner lifecycleOwner;

    public ChatRoomViewModel() {
        this.messageMutableLiveData = new MutableLiveData<>();
        this.chatRoomRepository = new ChatRoomRepository();
    }

    public void setLifecycleOwner(@NonNull LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        this.chatRoomRepository.messageMutableLiveData.observe(lifecycleOwner, message -> {
            if(message != null) {
                messageMutableLiveData.postValue(message);
            }
        });
    }
}