package com.pparreno.ligchat.chatroom.model;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.Map;

public class User implements Serializable {

    String email;
    String displayName;

    public User() {
    }

    public User(@NonNull String email, @NonNull String displayName) {
        this.email = email;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static User createUserFromFirebase(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return new User(firebaseUser.getEmail(), firebaseUser.getDisplayName());
    }
}
