package com.pparreno.ligchat.chatroom.model;

import java.io.Serializable;
import java.util.Map;

public class Message implements Serializable {
    String message;
    User owner;
    Map<String, String> timestamp;

    public Message() {
    }

    public Message(String message, User owner, Map<String, String> timeStamp) {
        this.message = message;
        this.owner = owner;
    }

    public Map<String, String> getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public User getOwner() {
        return owner;
    }
}
