package com.pparreno.ligchat.chatroom.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.pparreno.ligchat.R;
import com.pparreno.ligchat.chatroom.model.Message;
import com.pparreno.ligchat.chatroom.model.User;
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
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v,
                                       int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(recyclerView.getAdapter().getItemCount() > 0)
                            {
                                recyclerView.smoothScrollToPosition(
                                        recyclerView.getAdapter().getItemCount() - 1);
                            }

                        }
                    }, 100);
                }
            }
        });
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
                sendMessage();
                View focusedView = getActivity().getCurrentFocus();
                if (focusedView != null) {
                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        messageField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {
                    sendMessage();
                }
                return false;
            }
        });
        messageField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    sendButton.setEnabled(true);
                } else {
                    sendButton.setEnabled(false);
                }
            }
        });

    }

    private void sendMessage() {
        String messageString = messageField.getText().toString();
        if (messageString.length() > 0) {
            Message message = new Message(messageField.getText().toString(), User.createUserFromFirebase());
            mViewModel.sendNewChatMessage(message);
            messageField.getText().clear();
            messageField.clearFocus();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        chatRoomAdapter = new ChatRoomAdapter(Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail()));
        recyclerView.setAdapter(chatRoomAdapter);
        this.mViewModel.setLifecycleOwner(getViewLifecycleOwner());
        this.mViewModel.messagesMutableLiveData.observe(getViewLifecycleOwner(), messages -> {
            if (chatRoomAdapter != null) {
                chatRoomAdapter.setMessages(messages);
                this.recyclerView.smoothScrollToPosition(chatRoomAdapter.getItemCount() - 1);
            }
        });
        this.mViewModel.messageMutableLiveData.observe(getViewLifecycleOwner(), message -> {
            if (chatRoomAdapter != null) {
                chatRoomAdapter.addMessage(message);
                this.recyclerView.smoothScrollToPosition(chatRoomAdapter.getItemCount() - 1);
            }
        });

    }
}