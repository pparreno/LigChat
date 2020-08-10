package com.pparreno.ligchat.chatroom.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pparreno.ligchat.chatroom.model.Message;
import com.pparreno.ligchat.chatroom.repository.ChatRoomRepository;

import java.util.List;

public class ChatRoomViewModel extends ViewModel {

    public MutableLiveData<Message> messageMutableLiveData;
    public MutableLiveData<List<Message>> messagesMutableLiveData;
    public MutableLiveData<Boolean> shouldClearListFlagMLiveData;
    private ChatRoomRepository chatRoomRepository;
    LifecycleOwner lifecycleOwner;

    public ChatRoomViewModel() {
        this.messageMutableLiveData = new MutableLiveData<>();
        this.messagesMutableLiveData = new MutableLiveData<>();
        this.shouldClearListFlagMLiveData = new MutableLiveData<>();
        this.chatRoomRepository = new ChatRoomRepository();
    }

    public void setLifecycleOwner(@NonNull LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        this.chatRoomRepository.messagesMutableLiveData.observe(lifecycleOwner, messages -> {
            if(messages != null) {
                messagesMutableLiveData.postValue(messages);
            }
        });
        this.chatRoomRepository.shouldClearListFlagMLiveData.observe(lifecycleOwner, shouldClearListFlag -> {
            this.shouldClearListFlagMLiveData.postValue(shouldClearListFlag);
        });
        this.messageMutableLiveData.observe(lifecycleOwner, message -> {
            this.messageMutableLiveData.postValue(message);
        });
    }

    public void sendNewChatMessage(@NonNull Message message)
    {
        this.chatRoomRepository.sendChatMessage(message);
    }
}