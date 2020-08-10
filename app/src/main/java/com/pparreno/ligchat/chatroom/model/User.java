package com.pparreno.ligchat.chatroom.model;

import java.io.Serializable;
import java.util.Map;

public class User implements Serializable {

    String email;
    String displayName;
    Map<String, String> timestamp;

    public User() {
    }

    public User(String email, String displayName) {
        this.email = email;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }
}
