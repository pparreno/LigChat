package com.pparreno.ligchat.chatroom.model;

import androidx.annotation.NonNull;

import java.io.Serializable;


public class Message implements Serializable {
    String message;
    User owner;

    public Message() {
    }

    public Message(@NonNull String message, @NonNull User owner) {
        this.message = message;
        this.owner = owner;
    }

    public String getMessage() {
        return message;
    }

    public User getOwner() {
        return owner;
    }


}
