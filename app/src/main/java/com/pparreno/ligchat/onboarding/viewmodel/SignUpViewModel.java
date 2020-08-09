package com.pparreno.ligchat.onboarding.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.pparreno.ligchat.onboarding.repository.SignUpRepository;

public class SignUpViewModel extends ViewModel {
    private MutableLiveData<FirebaseUser> user;

    private SignUpRepository signUpRepository;

    public SignUpViewModel() {
        this.signUpRepository = new SignUpRepository();
    }

    public void RegisterUser(@NonNull String username, @NonNull String email, @NonNull String password)
    {

    }

}