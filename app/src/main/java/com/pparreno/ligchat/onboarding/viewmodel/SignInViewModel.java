package com.pparreno.ligchat.onboarding.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pparreno.ligchat.onboarding.repository.SignInRepository;

public class SignInViewModel extends ViewModel {
    private LifecycleOwner lifecycleOwner;

    private SignInRepository signInRepository;

    public MutableLiveData<Boolean> isAuthenticated;
    public MutableLiveData<Boolean> flagAuthProcessOnGoing;

    public SignInViewModel() {
        this.signInRepository = new SignInRepository();
        this.isAuthenticated = new MutableLiveData<>();
        this.flagAuthProcessOnGoing = new MutableLiveData<>();
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    public void signInUser(@NonNull String email, @NonNull String password){
        this.flagAuthProcessOnGoing.postValue(true);
        this.signInRepository.signInUser(email, password).observe(lifecycleOwner, firebaseUser -> {
            if(firebaseUser != null){
                isAuthenticated.postValue(true);
            } else {
                isAuthenticated.postValue(false);
            }
            flagAuthProcessOnGoing.postValue(false);
        });
    }
}