package com.pparreno.ligchat.chatroom.ui.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pparreno.ligchat.R;

public class ChatRoomViewHolder extends RecyclerView.ViewHolder {

    public TextView ownerTextView;
    public TextView messageTextView;

    public ChatRoomViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ownerTextView = itemView.findViewById(R.id.owner_text);
        this.messageTextView = itemView.findViewById(R.id.chat_text);
    }
}
