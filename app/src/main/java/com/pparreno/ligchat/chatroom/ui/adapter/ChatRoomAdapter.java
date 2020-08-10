package com.pparreno.ligchat.chatroom.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pparreno.ligchat.R;
import com.pparreno.ligchat.chatroom.model.Message;
import com.pparreno.ligchat.chatroom.ui.view.ChatRoomViewHolder;
import com.pparreno.ligchat.constants.MessageViewType;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomViewHolder> {

    private List<Message> messages;
    private String email;

    public ChatRoomAdapter(@NonNull String email) {
        this.messages = new ArrayList<>();
        this.email = email;
    }

    public void addMessage(@NonNull Message message) {
        if (!this.messages.contains(message)) {
            this.messages.add(message);
            notifyDataSetChanged();
        }
    }

    public void setMessages(@NonNull List<Message> messages) {
        clearData();
        this.messages = new ArrayList<>(messages);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.messages.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (viewType == MessageViewType.OWNER_TYPE) {
            itemView = layoutInflater.inflate(R.layout.recycler_chat_item_right, parent, false);
        } else {
            itemView = layoutInflater.inflate(R.layout.recycler_chat_item_left, parent, false);
        }

        return new ChatRoomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomViewHolder holder, int position) {
        Message message = getItem(position);

        holder.messageTextView.setText(message.getMessage());
        holder.ownerTextView.setText(message.getOwner().getDisplayName());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = getItem(position);
        if (message.getOwner().getEmail().compareTo(email) == 0) {
            return MessageViewType.OWNER_TYPE;
        } else {
            return MessageViewType.EXTERNAL_TYPE;
        }
    }

    private Message getItem(int position) {
        return messages.get(position);
    }
}
