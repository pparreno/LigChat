package com.pparreno.ligchat.chatroom.ui.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pparreno.ligchat.R;
import com.pparreno.ligchat.chatroom.viewmodel.ChatRoomViewModel;

public class ChatRoomFragment extends Fragment {

    private ChatRoomViewModel mViewModel;

    public static ChatRoomFragment newInstance() {
        return new ChatRoomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_room_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChatRoomViewModel.class);
        // TODO: Use the ViewModel
    }

}