package com.pparreno.ligchat.chatroom.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.pparreno.ligchat.R;
import com.pparreno.ligchat.chatroom.ui.adapter.ChatRoomAdapter;
import com.pparreno.ligchat.chatroom.viewmodel.ChatRoomViewModel;

import java.util.Objects;

public class ChatRoomFragment extends Fragment {

    private ChatRoomViewModel mViewModel;

    private Button sendButton;
    private EditText messageField;

    private RecyclerView recyclerView;
    private ChatRoomAdapter chatRoomAdapter;

    public static ChatRoomFragment newInstance() {
        return new ChatRoomFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chat_room_fragment, container, false);
        sendButton = v.findViewById(R.id.send_button);
        messageField = v.findViewById(R.id.message_field);
        recyclerView = v.findViewById(R.id.recyclerview);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChatRoomViewModel.class);
        // TODO: Use the ViewModel
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReferenceFromUrl("https://ligchat-d8920.firebaseio.com/room/1");
//
//                DatabaseReference messagesRef = myRef.child("messages");
//
//                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//                User user = new User(firebaseUser.getEmail(), firebaseUser.getDisplayName());
//                Message message = new Message(messageField.getText().toString(), user);
//                messagesRef.push().setValue(message);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatRoomAdapter = new ChatRoomAdapter(Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail()));
        recyclerView.setAdapter(chatRoomAdapter);
        this.mViewModel.setLifecycleOwner(getViewLifecycleOwner());
        this.mViewModel.messageMutableLiveData.observe(getViewLifecycleOwner(), message -> {
            if(chatRoomAdapter != null)
            {
                chatRoomAdapter.addMessage(message);
                chatRoomAdapter.notifyDataSetChanged();
            }
        });
    }
}